package stundet.db.demo.apicalling;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import stundet.db.demo.model.GroupModel;
import stundet.db.demo.model.MessageModel;

public interface Api {

        String BASE_URL = "http://192.168.43.125/demo/";

        @GET("getGroups.php")
        Call<List<GroupModel>> getGroups();

    @GET("createEmpType.php")
    Call<List<MessageModel>> insertEmpType(@Query("title")String title, @Query("level")String  level, @Query("detail")String  detail, @Query("status")String  status, @Query("admin_id")String  admin_id);

}

