package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult<T>{
    private  Integer code;//响应码
    private  String msg;//简短的信息
    private T data;//数据

//    public ResponseResult(int code, String msg){
//        this(code,msg,null);
//    }
//    public ResponseResult(T data){
//        this(200,"ok",data);
//    }
//    public static  final ResponseResult<Void> SUCCUESS=new ResponseResult(200,"OK");
//    public static  final ResponseResult<Void> FAIL=new ResponseResult(201,"OK");
    public static final ResponseResult<Void> SUCCESS
            =ResponseResult.<Void>builder().code(200).msg("success").build();
    public static final ResponseResult<Void> FAILED
            =ResponseResult.<Void>builder().code(500).msg("failed").build();
    public static ResponseResult<Void> ok(){
        return ResponseResult.<Void>builder().code(200).msg("success").build();
    }
    public static<T>  ResponseResult<T> ok(T data){
        return ResponseResult.<T>builder().code(200).msg("success").data(data).build();
    }
}
