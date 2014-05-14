package org.museosdetenerife;

import android.app.Application;

public class Globals extends Application {
  
   // Global variable
   private String id_museo;
 
   public void setIdMuseo(String _id_museo){
     this.id_museo=_id_museo;
   }
   public String getIdMuseo(){
     return this.id_museo;
   }
}
