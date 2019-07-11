package com.example.dgfab.APIanURLs;

import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.GET_Services;
import com.example.dgfab.AllParsings.Registration_only;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

//    @FormUrlEncoded
//    @POST(REtroURls.Getsubusertype)
//    Call<Type_Sub_Users> TYPE_SUB_USERS_CALL(
//            @Field("type_id") String type_of_user
//    );
//
    @FormUrlEncoded
    @POST(REtroURls.Get_Services)
    Call<GET_Services> Get_ServicesUsersCall(
            @Field("type_id") String type_of_user
    );

    @FormUrlEncoded
    @POST(REtroURls.Add_Services)
    Call<Add_Services> Add_Services_Call(
            @Field("type_id") String type_of_user,
            @Field("service") String service,
            @Field("image") String image
    );

//
    @FormUrlEncoded
    @POST(REtroURls.Registration)
    Call<Registration_only> REGISTRATION_ONLY_CALL(
            @Field("user_type") String user_type,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("company_name") String company_name,
            @Field("password") String password,
            @Field("sub_type") String sub_type,
            @Field("sub_type") String buss_type,
            @Field("sub_type") String sele_subser);
// @FormUrlEncoded
//    @POST(REtroURls.Registration)
//    Call<GET_Services> Ragistration(
//         @Field("type_id") String type_of_user
// );

}
