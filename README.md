# bitmap-String
>>图片转换文字

 * 转换成字符串保存
 
~~~
public String bitmap2String(Bitmap bitmap){
       if (bitmap==null){
           try {
               throw  new IllegalArgumentException("参数异常");
           } catch (IllegalArgumentException e) {
               e.printStackTrace();
           }
           return null;
       }
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        string= Base64.encodeToString(bytes,Base64.DEFAULT);
       
        return string;
    }

 ~~~
 * 字符串转换成bitmap
 ~~~
   public Bitmap string2Bitmap(String string) {
         // 将字符串转换成Bitmap类型
         if (string==null){
             try {
                 throw  new IllegalArgumentException("参数异常");
             } catch (IllegalArgumentException e) {
                 e.printStackTrace();
             }
             return null;
         }
         Bitmap bitmap = null;
         try {
             byte[] bitmapArray;
             bitmapArray = Base64.decode(string, Base64.DEFAULT);
             bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                     bitmapArray.length);
         } catch (Exception e) {
             e.printStackTrace();
         }
        
         return bitmap;
     }
 ~~~
 测试是读取速度有点慢
