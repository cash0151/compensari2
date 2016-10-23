package compensation.compensationsystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Irina.B on 20.10.2016.
 */
public class Company implements Parcelable
{

    @SerializedName("Id")
    private int company_id;

    @SerializedName("Denumire")
    private String company_name;

    public Company(int company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    protected Company(Parcel in) {
        company_id = in.readInt();
        company_name = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(company_id);
        dest.writeString(company_name);
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }


}
