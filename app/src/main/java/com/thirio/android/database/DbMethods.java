package com.thirio.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.thirio.android.model.Order;
import com.thirio.android.model.User;

import java.util.ArrayList;
import java.util.List;

public class DbMethods implements DbConstants {

    Context context;
    DbHelper dbHelper;
    SQLiteDatabase db;
    String TAG = "DATABASE";

    public DbMethods(Context context){
        this.context=context;
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }


    public long insertUser(String name, String sex,double height, int weight, int age, String contact) {
        ContentValues values= new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_SEX, sex);
        values.put(COL_HEIGHT, height*100);
        values.put(COL_WEIGHT, weight);
        values.put(COL_AGE, age);
        values.put(COL_CONTACT, contact);
        int id= (int) db.insert(TBL_USER,null,values);
        Log.d(TAG,values.toString());
        return id;
    }

    public Cursor queryUser(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return  db.query(TBL_USER,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public void deleteUser(long id){
        db.delete(TBL_USER,COL_ID+" = ? ",new String[]{id+""});
    }

    public void deleteAllUsers() {
        db.delete(TBL_USER, null,null);
    }
    public  List<String> getAllUsers(){
        List<String>names = new ArrayList<>();
        String query="SELECT * FROM "+ TBL_USER;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(COL_NAME));
                names.add(name);
            }while (cursor.moveToNext());
        }
        return names;
    }
    public List<User> getAllUserDetails(){
        List<User>users = new ArrayList<>();
        String query="SELECT * FROM "+ TBL_USER;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                User u=new User();
                String name=cursor.getString(cursor.getColumnIndex(COL_NAME));
                u.setName(name);
                u.setAge(cursor.getInt(cursor.getColumnIndex(COL_AGE)));
                u.setHeight(cursor.getInt(cursor.getColumnIndex(COL_HEIGHT)));
                u.setUserID(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                u.setWeight(cursor.getInt(cursor.getColumnIndex(COL_WEIGHT)));
            }while (cursor.moveToNext());
            return users;
        }
        return null;
    }
    public  List<String> getAllContacts(){
        List<String>contacts = new ArrayList<>();
        String query="SELECT * FROM "+ TBL_USER;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(COL_CONTACT));
                contacts.add(name);
            }while (cursor.moveToNext());
        }
        return contacts;
    }
    public int ifAlreadyExists(String name){
        String query="SELECT * FROM "+ TBL_USER +" WHERE "+ COL_NAME + " = '"+name+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
                long id=cursor.getInt(cursor.getColumnIndex(COL_ID));
                Log.d("CHECK USER","exists "+id);
                return (int)(id);
        }
        Log.d("CHECK USER","doesn't exist");
        return -1;
    }
    public int getHeight(String name){
        String query="SELECT * FROM "+ TBL_USER +" WHERE "+ COL_NAME + " = '"+name+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int ht=cursor.getInt(cursor.getColumnIndex(COL_HEIGHT));
            return (int)(ht);
        }
        return -1;
    }
    public int getWeight(String name){
        String query="SELECT * FROM "+ TBL_USER +" WHERE "+ COL_NAME + " = '"+name+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int wt=cursor.getInt(cursor.getColumnIndex(COL_WEIGHT));
            return (int)(wt);
        }
        return -1;
    }
    public String getSex(String name){
        String query="SELECT * FROM "+ TBL_USER +" WHERE "+ COL_NAME + " = '"+name+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            String sex=cursor.getString(cursor.getColumnIndex(COL_SEX));
            return sex;
        }
        return "Male";
    }
    public int getAge(String name){
        String query="SELECT * FROM "+ TBL_USER +" WHERE "+ COL_NAME + " = '"+name+"'";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int age=cursor.getInt(cursor.getColumnIndex(COL_AGE));
            return age;
        }
        return -1;
    }
    public List<Order> getAllOrders(){
        String query="SELECT * FROM "+ TBL_DIET_USER_YET_TO_BE_FINALIZED ;
        Cursor cursor=db.rawQuery(query,null);
        List<Order> orders=new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Order o= new Order();
                o.setBreads(cursor.getString(cursor.getColumnIndex(COL_BREADS)));
                o.setMainCourse(cursor.getString(cursor.getColumnIndex(COL_MAIN_COURSE)));
                o.setSalads(cursor.getString(cursor.getColumnIndex(COL_SALAD)));
                o.setSides(cursor.getString(cursor.getColumnIndex(COL_SIDES)));
                o.setDiet(cursor.getInt(cursor.getColumnIndex(COL_DIET)));
                o.setOrderID(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                int id=cursor.getInt(cursor.getColumnIndex(COL_USERID));

                Log.d("USER ID",id+"");
                o.setUserid(id);
                String q="SELECT * FROM "+ TBL_USER+" WHERE "+ COL_ID + " = "+id;
                Cursor c=db.rawQuery(q,null);
                if(c.moveToFirst()){
                    o.setName(c.getString(c.getColumnIndex(COL_NAME)));
                }
                else{
                    o.setName("Unknown");
                }
                orders.add(o);
            }while (cursor.moveToNext());
            return orders;
        }
        return null;
    }
    public long insertOrderNotPaid(int id, String mainCourse,String salads, String sides, String breads,int diet) {
        ContentValues values= new ContentValues();
        values.put(COL_USERID, id);
        values.put(COL_MAIN_COURSE, mainCourse);
        values.put(COL_SALAD, salads);
        values.put(COL_SIDES, sides);
        values.put(COL_BREADS, breads);
        values.put(COL_DIET,diet);
        int idd= (int) db.insert(TBL_DIET_USER_YET_TO_BE_FINALIZED,null,values);
        Log.d(TAG+ "not paid",values.toString());
        return idd;
    }
    public boolean deleteOrderNotPaid(int id){
        return db.delete(TBL_DIET_USER_YET_TO_BE_FINALIZED,COL_ID + "=" + id,null)>0;
    }
    public void deleteAllOrderNotPaid() {
        db.delete(TBL_DIET_USER_YET_TO_BE_FINALIZED, null,null);
    }

    public long insertOrder(int id, String mainCourse,String salads, String sides, String breads,int diet) {
        ContentValues values= new ContentValues();
        values.put(COL_USERID, id);
        values.put(COL_MAIN_COURSE, mainCourse);
        values.put(COL_SALAD, salads);
        values.put(COL_SIDES, sides);
        values.put(COL_BREADS, breads);
        values.put(COL_DIET,diet);
        int idd= (int) db.insert(TBL_DIET_USER,null,values);
        Log.d(TAG+ "Paid",values.toString());
        return idd;
    }
    public void paymentSuccess(){
        List<Order> order=getAllOrders();
        if(order!=null){
            for(int i=0;i<order.size();i++){
                insertOrder(order.get(i).getUserid(),order.get(i).getMainCourse(),order.get(i).getSalads(),order.get(i).getSides(),order.get(i).getBreads(),order.get(i).getDiet());
            }

        }
    }

}
