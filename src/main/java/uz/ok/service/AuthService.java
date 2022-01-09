package uz.ok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ok.dao.IUserRepo;
import uz.ok.dto.request.RegisterDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.entity.enums.SystemRoleName;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;

    /*TODO code will be written*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return iUserRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public ApiResponse registerUser(RegisterDto registerDto){
        if (iUserRepo.existsByEmail(registerDto.getEmail()))
        return new ApiResponse("Sorry this user has already been registered!", false);
        User user = new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_USER
        );
        int code = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(code).substring(0,4));
        iUserRepo.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("User successfully saved!", true);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("karimzanovojbek292@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Accountni tasdiqlang!");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode){
        Optional<User> optionalUser = iUserRepo.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())){
                user.setEnabled(true);
                iUserRepo.save(user);
                return new ApiResponse("Account activated", true);
            }
            return new ApiResponse("Code is incorrect!", false);
        }
        return new ApiResponse("This user doesn't exist!", false);
    }



}
