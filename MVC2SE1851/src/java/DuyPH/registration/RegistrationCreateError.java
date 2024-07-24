/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuyPH.registration;

import java.io.Serializable;

/**
 *
 * @author whisa
 */
public class RegistrationCreateError implements Serializable{
    protected String usenameLenghError;
    protected String passwordLenghError;
    protected String fullNameLenghError;
    protected String confirmNotMatched;
    protected String usenameIsExisted;

    public RegistrationCreateError() {
    }
    
    public String getUsenameLenghError() {
        return usenameLenghError;
    }

    public void setUsenameLenghError(String usenameLenghError) {
        this.usenameLenghError = usenameLenghError;
    }

    public String getPasswordLenghError() {
        return passwordLenghError;
    }

    public void setPasswordLenghError(String passwordLenghError) {
        this.passwordLenghError = passwordLenghError;
    }

    public String getFullNameLenghError() {
        return fullNameLenghError;
    }

    public void setFullNameLenghError(String fullNameLenghError) {
        this.fullNameLenghError = fullNameLenghError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmLenghError) {
        this.confirmNotMatched = confirmLenghError;
    }

    public String getUsenameIsExisted() {
        return usenameIsExisted;
    }

    public void setUsenameIsExisted(String usenameIsExisted) {
        this.usenameIsExisted = usenameIsExisted;
    }
    
    
}
