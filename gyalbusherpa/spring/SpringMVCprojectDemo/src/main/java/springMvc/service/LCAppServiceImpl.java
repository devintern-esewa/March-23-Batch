package springMvc.service;

import org.springframework.stereotype.Service;

@Service
public class LCAppServiceImpl implements LcAppService {

    String LC_APP_FORMULA = "FLAME";

    public String calculateLove(String userName, String crushName) {
        int userAndCrushNameCount = (userName + crushName).toCharArray().length;
        int formulaCount = LC_APP_FORMULA.toCharArray().length;

        int rem = userAndCrushNameCount % formulaCount;

        char resultChar = LC_APP_FORMULA.charAt(rem);

        String result = whatsBetweenUs(resultChar);

        return result;
    }

    public String whatsBetweenUs(char calculationResult) {

        String result = null;

        if (calculationResult == 'F') {
            result = "friend";
        } else if (calculationResult == 'L') {
            result = "love";
        } else if (calculationResult == 'A') {
            result = "affection";
        } else if (calculationResult == 'M') {
            result = "marriage";
        } else if (calculationResult == 'E') {
            result = "enemy";
        }

        return result;
    }
}
