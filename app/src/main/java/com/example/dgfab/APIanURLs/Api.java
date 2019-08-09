package com.example.dgfab.APIanURLs;

import com.example.dgfab.AllParsings.Accept_Decline;
import com.example.dgfab.AllParsings.AddSubService;
import com.example.dgfab.AllParsings.Add_Services;
import com.example.dgfab.AllParsings.All_Country_State;
import com.example.dgfab.AllParsings.All_Sent_Request;
import com.example.dgfab.AllParsings.All_State_found_responce;
import com.example.dgfab.AllParsings.CommingRequest;
import com.example.dgfab.AllParsings.Connection_Requests;
import com.example.dgfab.AllParsings.DestroyingConnections;
import com.example.dgfab.AllParsings.Friends;
import com.example.dgfab.AllParsings.GET_Services;
import com.example.dgfab.AllParsings.GetProducts;
import com.example.dgfab.AllParsings.Get_Cities;
import com.example.dgfab.AllParsings.MyInfo;
import com.example.dgfab.AllParsings.MyServices;
import com.example.dgfab.AllParsings.MySubservices;
import com.example.dgfab.AllParsings.ProductReply;
import com.example.dgfab.AllParsings.Registration_only;
import com.example.dgfab.AllParsings.Searching_Manufacturers;
import com.example.dgfab.BusinessDashboard.Search_All_Users;

import java.lang.reflect.Array;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
//    @FormUrlEncoded
//    @POST(REtroURls.Get_Services)
//    Call<CreatedStaff> CREATED_STAFF_CALL(
//            @Field("staff_id") String staff_id,
//            @Field("name") String name,
//            @Field("email") String email
//            @Field("password") String password
//            @Field("type_id") String type_of_user
//    );

    @FormUrlEncoded
    @POST(REtroURls.Add_Services)
    Call<Add_Services> Add_Services_Call(
            @Field("type_id") String type_of_user,
            @Field("service") String service,
            @Field("image") String image
    );
    @FormUrlEncoded
    @POST(REtroURls.AddProduct)
    Call<ProductReply> PRODUCT_REPLY_CALL(
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("product_name") String product_name,
            @Field("description") String description,
            @Field("mrp") String mrp,
            @Field("price") String price,
            @Field("stock") String stock,
            @Field("qty") String qty,
            @Field("title") String title,
            @Field("discription") String discription

            );

    @FormUrlEncoded
    @POST(REtroURls.Getproduct)
    Call<GetProducts> GET_PRODUCTS_CALL(
            @Field("user_id") String user_id
            );


  @FormUrlEncoded
    @POST(REtroURls.Getsub_Subservices)
    Call<MySubservices> MY_SUBSERVICES_CALL(
            @Field("subservice_id") String subservice_id

    );
  @FormUrlEncoded
    @POST(REtroURls.Add_sub_subservices)
    Call<AddSubService> ADD_SUB_SERVICE_CALL(
            @Field("subservice_id") String subservice_id,
            @Field("name") String name

    );

    @FormUrlEncoded
    @POST(REtroURls.Getservicesbyuser)
    Call<MyServices> MY_SERVICES_CALL(
            @Field("user_id") int user_id

    );

//
    @FormUrlEncoded
    @POST(REtroURls.Registration)
    Call<Registration_only> REGISTRATION_ONLY_CALL(
            @Field("country") String country,
            @Field("state") String state,
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String full_name,
            @Field("lastname") String lastname,
            @Field("company_name") String company_name,
            @Field("pin") String pin,
            @Field("user_type") String buss_type,
            @Field("sub_type") String sele_subser,
            @Field("city") String city);


    @GET(REtroURls.Country_url)
    Call<All_Country_State> Get_Country_Call(@Query("name") String name );
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.State_url)
    Call<All_State_found_responce> Get_State_Call(
            @Field("country_id") String city,
            @Field("name") String name);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.City_url)
    Call<Get_Cities> GET_CITIES_CALL(
            @Field("state_id") String state_id,
            @Field("name") String name);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Searchusers)
    Call<Searching_Manufacturers> SEARCH_ALL_USERS_CALL(
            @Field("name") String name,
            @Field("user_id") String user_id

    );@Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Getuserbyid)
    Call<MyInfo> MY_INFO_CALL(
            @Field("user_id") String user_id

    );


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Getreceivedrequest)
    Call<CommingRequest> COMMING_REQUEST_CALL(
            @Field("user_id") String user_id);


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Friendsrequests)
    Call<Friends> FRIENDS_CALL(
            @Field("user_id") String user_id);




    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Sendrequest)
    Call<Connection_Requests> CONNECTION_REQUESTS_CALL(
            @Field("senderid") String senderid,
            @Field("receiverid") String receiverid



    );

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Acceptdeclinerequest)
    Call<Accept_Decline> ACCEPT_DECLINE_CALL(
            @Field("senderid") String senderid,
            @Field("receiverid") String receiverid,
            @Field("status") String status
    );
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Getsentrequest)
    Call<All_Sent_Request> ALL_SENT_REQUEST_CALL(
            @Field("user_id") String senderid
    );

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(REtroURls.Deleterequest)
    Call<DestroyingConnections> DESTROYING_CONNECTIONS_CALL(
            @Field("id") String receiverid
    );
// @FormUrlEncoded
//    @POST(REtroURls.Registration)
//    Call<GET_Services> Ragistration(
//         @Field("type_id") String type_of_user
// );

}
