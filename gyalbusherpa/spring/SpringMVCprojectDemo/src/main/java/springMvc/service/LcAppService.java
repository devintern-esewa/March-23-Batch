package springMvc.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public interface LcAppService {
    String calculateLove(String userName, String crushName);

    String whatsBetweenUs(char calculationResult);
}
