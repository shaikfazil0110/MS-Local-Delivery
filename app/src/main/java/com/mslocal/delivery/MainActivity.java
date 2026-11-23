package com.mslocal.delivery;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.net.URLEncoder;

public class MainActivity extends Activity {
  final int green=Color.rgb(0,105,58), pale=Color.rgb(245,249,246), ink=Color.rgb(19,48,36);
  boolean tamil=true;
  final String[] en={"Grocery","Vegetables","Hotel Food","Medicine","Parcel","Meat","Chicken","Fish","Mutton"};
  final String[] ta={"மளிகை பொருட்கள்","காய்கறிகள்","ஹோட்டல் உணவு","மருந்து","பார்சல்","இறைச்சி","கோழி இறைச்சி","மீன்","ஆட்டு இறைச்சி"};
  final int[] pics={R.drawable.grocery,R.drawable.vegetables,R.drawable.food,R.drawable.medicine,R.drawable.parcel,R.drawable.meat,R.drawable.chicken,R.drawable.fish,R.drawable.mutton};
  LinearLayout root;
  int dp(float n){return (int)(n*getResources().getDisplayMetrics().density+0.5f);}
  GradientDrawable bg(int color,int radius){GradientDrawable d=new GradientDrawable();d.setColor(color);d.setCornerRadius(dp(radius));return d;}
  TextView text(String s,int size,int color,boolean bold){TextView v=new TextView(this);v.setText(s);v.setTextSize(size);v.setTextColor(color);v.setGravity(Gravity.CENTER_VERTICAL);if(bold)v.setTypeface(null,Typeface.BOLD);return v;}
  LinearLayout vertical(){LinearLayout l=new LinearLayout(this);l.setOrientation(1);return l;}
  LinearLayout.LayoutParams lp(int w,int h){return new LinearLayout.LayoutParams(w<0?w:dp(w),h<0?h:dp(h));}
  void gap(LinearLayout l,int h){View v=new View(this);l.addView(v,lp(1,h));}
  @Override public void onCreate(Bundle b){super.onCreate(b);render();}
  void render(){
    ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);scroll.setBackgroundColor(Color.rgb(250,252,250));
    root=vertical();root.setPadding(dp(16),dp(20),dp(16),dp(24));scroll.addView(root);setContentView(scroll);
    LinearLayout header=vertical();header.setPadding(dp(20),dp(26),dp(20),dp(24));header.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{green,Color.rgb(25,158,78)}));
    TextView top=text("M S  LOCAL DELIVERY",24,Color.WHITE,true);header.addView(top);
    gap(header,12);header.addView(text(tamil?"இளையான்குடி மற்றும் அருகிலுள்ள கிராமங்கள்":"Ilaiyangudi & nearby villages",15,Color.WHITE,false));
    gap(header,10);TextView hours=text(tamil?"🕒  சேவை நேரம்: காலை 6:00 முதல் இரவு 10:00 வரை":"🕒  Service Hours: 6:00 AM – 10:00 PM",14,Color.WHITE,true);header.addView(hours);
    gap(header,12);TextView change=text(tamil?"English  /  தமிழ்":"தமிழ்  /  English",14,Color.WHITE,true);change.setOnClickListener(v->{tamil=!tamil;render();});header.addView(change);header.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{green,Color.rgb(19,149,76)}){{setCornerRadius(dp(22));}});root.addView(header,lp(-1,-2));
    gap(root,22);root.addView(text(tamil?"உங்கள் தேவைகள், எங்கள் சேவை":"Everything you need, delivered",21,ink,true));gap(root,6);root.addView(text(tamil?"பிரிவைத் தேர்ந்தெடுத்து WhatsApp மூலம் ஆர்டர் செய்யுங்கள்":"Choose a category and order via WhatsApp",13,Color.DKGRAY,false));gap(root,16);
    for(int row=0;row<(en.length+1)/2;row++){
      if(row==3){gap(root,10);root.addView(text(tamil?"இறைச்சி வகைகள்":"Fresh meat & seafood",20,ink,true));gap(root,12);}
      LinearLayout line=new LinearLayout(this);line.setOrientation(0);
      for(int col=0;col<2;col++){
        int i=row*2+col;if(i>=en.length)break;
        LinearLayout card=vertical();card.setPadding(dp(7),dp(7),dp(7),dp(14));card.setBackground(bg(Color.WHITE,20));card.setElevation(dp(3));
        ImageView img=new ImageView(this);img.setImageResource(pics[i]);img.setScaleType(ImageView.ScaleType.CENTER_CROP);img.setBackground(bg(pale,15));img.setClipToOutline(true);card.addView(img,lp(-1,120));gap(card,10);
        TextView title=text(en[i],16,ink,true);title.setGravity(Gravity.CENTER);card.addView(title);
        TextView subtitle=text(ta[i],12,green,false);subtitle.setGravity(Gravity.CENTER);card.addView(subtitle);
        final int index=i;card.setOnClickListener(v->order(index));
        LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(0,-2,1);cp.setMargins(dp(4),dp(2),dp(4),dp(14));line.addView(card,cp);
      }root.addView(line,lp(-1,-2));
    }
    gap(root,14);TextView whatsapp=button(tamil?"WhatsApp மூலம் ஆர்டர் செய்யவும்":"Order on WhatsApp",green);root.addView(whatsapp,lp(-1,54));whatsapp.setOnClickListener(v->order(-1));
    gap(root,12);TextView call=button(tamil?"📞  அழைக்கவும்: 8807169133":"📞  Call: 8807169133",ink);root.addView(call,lp(-1,48));call.setOnClickListener(v->startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:8807169133"))));
    gap(root,16);TextView info=text("💵  Cash on Delivery     •     UPI",15,green,true);info.setGravity(Gravity.CENTER);root.addView(info);gap(root,10);TextView footer=text(tamil?"📍 இளையான்குடி மற்றும் சுற்றியுள்ள கிராமங்கள்":"📍 Ilaiyangudi and nearby villages",13,green,true);footer.setGravity(Gravity.CENTER);root.addView(footer);
    gap(root,10);TextView note=text(tamil?"விலை மற்றும் டெலிவரி கட்டணம் WhatsApp-ல் உறுதி செய்யப்படும்.":"Prices and delivery charges are confirmed on WhatsApp.",13,Color.DKGRAY,false);note.setGravity(Gravity.CENTER);root.addView(note);
  }
  TextView button(String s,int color){TextView t=text(s,17,Color.WHITE,true);t.setGravity(Gravity.CENTER);t.setBackground(bg(color,18));t.setElevation(dp(2));return t;}
  EditText field(String hint,boolean multiline){EditText e=new EditText(this);e.setTextSize(15);e.setHint(hint);e.setSingleLine(!multiline);if(multiline){e.setMinLines(2);e.setGravity(Gravity.TOP);}return e;}
  void order(int index){
    LinearLayout form=vertical();form.setPadding(dp(16),dp(8),dp(16),0);
    EditText name=field(tamil?"உங்கள் பெயர் / Name":"Your name",false);
    EditText phone=field(tamil?"மொபைல் எண் / Mobile":"Mobile number",false);phone.setInputType(3);
    EditText address=field(tamil?"முகவரி / Delivery address":"Delivery address",true);
    EditText items=field(tamil?"தேவையான பொருட்கள் / Requirements":"Items / requirements",true);
    form.addView(name);form.addView(phone);form.addView(address);form.addView(items);
    String category=index<0?"General Request":en[index]+" / "+ta[index];
    AlertDialog dialog=new AlertDialog.Builder(this).setTitle(category).setView(form).setNegativeButton(tamil?"ரத்து":"Cancel",(d,w)->{}).setPositiveButton(tamil?"WhatsApp திறக்கவும்":"Open WhatsApp",null).create();
    dialog.show();dialog.getButton(-1).setOnClickListener(v->{
      if(name.getText().toString().trim().isEmpty()||phone.getText().toString().trim().isEmpty()||address.getText().toString().trim().isEmpty()||items.getText().toString().trim().isEmpty()){
        Toast.makeText(this,tamil?"அனைத்து விவரங்களையும் நிரப்பவும்":"Please fill all fields",Toast.LENGTH_SHORT).show();return;
      }
      String message="🛵 M S LOCAL DELIVERY\nNew Order\n\nCategory: "+category+"\nCustomer: "+name.getText()+"\nPhone: "+phone.getText()+"\nAddress: "+address.getText()+"\nItems / Requirements: "+items.getText()+"\n\nService Hours: 6:00 AM - 10:00 PM (Daily)\nPlease confirm price and delivery charge.";
      try {String url="https://wa.me/918807169133?text="+URLEncoder.encode(message,"UTF-8");startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));dialog.dismiss();}
      catch(Exception ex){Toast.makeText(this,"Unable to open WhatsApp link",Toast.LENGTH_LONG).show();}
    });
  }
}
