package compensation.compensationsystem.WebServices.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import compensation.compensationsystem.Models.Company;

/**
 * Created by Irina.B on 20.10.2016.
 */
public class LoginResponse
{
    @SerializedName("Message")
    private  String message;

    @SerializedName("LoginRealised")
    private boolean status;

    @SerializedName("ListOfRepresentedCompanies")
    private ArrayList<Company> represented_companies;

    public String getMessage() {
        return message;
    }

    public ArrayList<Company> getRepresented_companies() {
        return represented_companies;
    }

    public boolean isStatus() {
        return status;
    }
}
