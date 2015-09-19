package kms.test;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by KMS on 2015-03-01. 2015
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    String item_year[] = new String[35];

    String item_choice[] = {"타자","투수"};
    /*String item_fieldH[] = {"선수명","구단","타율","경기수","타수",
    "타석","득점","안타","2루타","3루타","홈런","루타","타점","도루","도루실패","희생타"
    ,"희생플라이","볼넷","고의4구","사구","삼진","병살","장타율","출루율","실책","도루성공률",
    "BB/K","장타/안타","멀티히트","OPS","득점권타율","대타타율","wOBA","wRRA","WAR"};
    String item_fieldP[] = {"선수명","구단","평균자책점","경기수","완봉","완투","승","패","세이브"
    ,"홀드","승률","상대타자수","투구수","이닝","피안타","피2루타","피3루타","피홈런","희생타",
    "희생플라이","볼넷","고의4구","사구","탈삼진","폭투","보크","실점","자책점","블론세이브","WHIP",
    "피안타율","QS","FIP","WAR"};*/
    String item_fieldH[] = {"선수명","구단","타율","경기수","타수",
            "득점","안타","2루타","3루타","홈런","루타","타점","도루","도루실패"
            ,"볼넷","사구","삼진","병살","장타율","출루율","실책"};
    String item_fieldP[] ={"선수명","구단","평균자책점","경기수","완투","완봉","승","패","세이브"
            ,"홀드","승률","상대타자수","이닝","피안타","피홈런",
            "볼넷","사구","탈삼진","실점","자책점"};
    HashMap<String,String> mapClass=new HashMap<>();
    HashMap<String,String> mapField=new HashMap<>();

    Spinner spinnerC;
    ArrayAdapter<String> listC;
    Spinner spinnerY;
    ArrayAdapter<String> listY;
    //Spinner spinnerF;
    ArrayAdapter<String> listF;

    String Field,Year,Tuta,orderBy,FieldCol,whereSentence;
    String[] pram = new String[2];

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3){

    }

    public void setSpinner(View rootView){//필드 스피너를 만들기 위한 스피너 메쏘드
        listC = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_item,item_choice);
        spinnerC = (Spinner)rootView.findViewById(R.id.spinClass);
        spinnerC.setAdapter(listC);
        spinnerC.setSelection(0);//타자 필드값을 먼저 호출하게 해준다.
        spinnerC.setOnItemSelectedListener(spinSelectedListener);
    }

    public void setSpinner(View rootView, String[] item_field){ // 선택된 필드의 스피너를 생성하는 메소드
        listF = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_item,item_field);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinField);
        spinner.setAdapter(listF);
        spinner.setOnItemSelectedListener(getSpinSelectedListener);// 선택값을 변수에 저장하도록 공통 onitemSelcted를 호출한다. 기존에는 this 사용
    }

    public AdapterView.OnItemSelectedListener spinSelectedListener = new AdapterView.OnItemSelectedListener()
        {
               @Override//필드 스피너를 위한 코드
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch(position){
                        case(0)://getView()가 매우 중요함!
                            setSpinner(getView(),item_fieldH);
                            Tuta = parent.getItemAtPosition(position).toString();
                            break;
                        case(1):
                            setSpinner(getView(),item_fieldP);
                            Tuta = parent.getItemAtPosition(position).toString();
                            break;
                    }
               }
               @Override
               public void onNothingSelected(AdapterView<?> parent) {
               }
        };
    public AdapterView.OnItemSelectedListener getSpinSelectedListener = new AdapterView.OnItemSelectedListener()
    {
        @Override//스피너 선택값을 저장하는 코드
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId()==R.id.spinYear) Year = parent.getItemAtPosition(position).toString();
            else if(parent.getId()==R.id.spinField) Field = parent.getItemAtPosition(position).toString();

            //Toast.makeText(getActivity(),Year,Toast.LENGTH_LONG).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "1";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
        //1번째
    }

    public SearchFragment() {
    }

    public void AssetManager(String year){
        AssetManager assetManager=getActivity().getResources().getAssets();
        File file = new File("data/data/kms.test/databases/"+year+".db");
        try {
            file.createNewFile();
            InputStream is = assetManager.open(file.getName());
            long filesize = is.available();
            byte[] tempdata = new byte[(int) filesize];
            is.read(tempdata);
            is.close();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(tempdata);
            fos.close();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getActivity(),"DB 불러오기 실패", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_fragment, container, false);

        //스피너에 띄울 연도를 만듬. 위에서 못만들어서 여기 있다. 메소드화 시켜서 불러와도 될것같다.
        item_year[0]="전체";
        for(int x=1982;x<=2015;x++){
            item_year[x-1981]=""+x;
        }

        //HashMap 선언 부분, 메소드 밖에서는 put이 안된다.
        mapClass.put("타자","Hitter");
        mapClass.put("투수","Pitcher");

        //spinner 출력 부분 , 연도만 따로 만들고 setSpinner 메소드를 통해 나머지 2개를 생성
        listY = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_item,item_year);
        spinnerY = (Spinner)rootView.findViewById(R.id.spinYear);
        spinnerY.setAdapter(listY);
        spinnerY.setOnItemSelectedListener(getSpinSelectedListener);
        setSpinner(rootView);

        //DB 꺼내오는 곳
        Button button = (Button) rootView.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //스피너와 입력된 값을 받아오는 부분
                //Year,Tuta,Field는 저장되어 있다.
                ArrayList<String> wholeField =new ArrayList<>();

                String range1;
                String range2;

                EditText rg1=(EditText)rootView.findViewById(R.id.range1);
                range1=rg1.getText().toString();
                EditText rg2=(EditText)rootView.findViewById(R.id.range2);
                range2=rg2.getText().toString();



                //mapField는 타자냐 투수에 따라 결정되어 하나만 입력
                //seletedField도 동시에 생성
                if(Tuta.equals("타자")){
                    int i=1;
                    for(String item:item_fieldH){
                        mapField.put(item,""+i);
                        wholeField.add(item);
                        i++;
                    }
                }
                else if(Tuta.equals("투수")) {
                    int i=1;
                    for (String item : item_fieldP) {
                        mapField.put(item, "" + i);
                        wholeField.add(item);
                        i++;
                    }
                }

                //Field값을 콜럼명과 일치하게 변환시켜준다.
                FieldCol="\""+mapField.get(Field)+"\"";
                orderBy="\""+mapField.get(Field)+"\" DESC";

                //AssetManager
                if(Year.equals("전체")) Year="0000";
                AssetManager(Year);

                //tablelayout을 만든다.
                TableLayout resultset = (TableLayout) rootView.findViewById(R.id.resultset);

                //이전 검색결과를 지워주도록 한번 클리어하고 시작한다.
                resultset.removeAllViews();

                String DBtitle = Year + ".db";
                SQLiteHandler handler = SQLiteHandler.open(getActivity(), DBtitle);

                //query 선택 하는 곳

                try {
                    Cursor c = null;
                    if (Year != null) {
                        if (Field.contains("선수명") || Field.equals("구단")) {
                            whereSentence = FieldCol + "=" + "\"" + range1 + "\"";
                            c = handler.select(mapClass.get(Tuta), null, whereSentence, null, null);
                        } else if (!range1.equals("") && !range2.equals("")) {
                            whereSentence = FieldCol + ">=? and " + FieldCol + "<=?";
                            pram[0] = range1;
                            pram[1] = range2;
                            c = handler.select(mapClass.get(Tuta), null, whereSentence, pram, orderBy);
                        } else if (!range1.equals("") && range2.equals("")) {
                            whereSentence = FieldCol + ">=" + range1;
                            c = handler.select(mapClass.get(Tuta), null, whereSentence, null, orderBy);
                        } else if (range1.equals("") && !range2.equals("")) {
                            whereSentence = FieldCol + "<=" + range2;
                            c = handler.select(mapClass.get(Tuta), null, whereSentence, null, orderBy);
                        } else {
                            // whereSentence = "remove";
                            c = handler.select(mapClass.get(Tuta), null, null, null, orderBy);
                        }
                    }/*else if(Year=="전체"){
                    for(int i=2015;i>=1982;i--){
                        //DB파일을 새로 정의하여 불러온다.
                        String DBtitle2=i+".db";
                        handler = SQLiteHandler.open(getActivity(),DBtitle2);
                        if(Field.equals("선수명")||Field.equals("구단")){
                            whereSentence=FieldCol+"="+"\""+range1+"\"";
                            c = handler.select(mapClass.get(Tuta),null,whereSentence,null,null);
                        }else if(!range1.equals("") && !range2.equals("")){
                            whereSentence= FieldCol+">=? and "+FieldCol+"<=?";
                            pram[0]=range1;
                            pram[1]=range2;
                            c = handler.select(mapClass.get(Tuta),null,whereSentence,pram,orderBy);
                        }else if(!range1.equals("") &&range2.equals("")){
                            whereSentence= FieldCol+">="+range1;
                            c = handler.select(mapClass.get(Tuta),null,whereSentence,null,orderBy);
                        }else if(range1.equals("")&& !range2.equals("")){
                            whereSentence= FieldCol+"<="+range2;
                            c = handler.select(mapClass.get(Tuta),null,whereSentence,null,orderBy);
                        }else{
                            // whereSentence = "remove";
                            c = handler.select(mapClass.get(Tuta),null,null,null,orderBy);
                        }
                    }
                }*/

                //제목을 만들어준다 꽤나 복잡한 구조임.
                    TableRow tableRow1=new TableRow(getActivity());
                    tableRow1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

                    for(int j = 0 ; j < wholeField.size() ; j++){
                        final TextView text = new TextView(getActivity());
                        text.setText(wholeField.get(j)+" ");
                        text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tableRow1.addView(text);
                    }
                    resultset.addView(tableRow1);

                //데이타를 DB에서 꺼내오고 제목 밑에 차곡차곡 쌓아 출력, 동적으로 작동

                    if (c != null) {
                        while (c.moveToNext()) {

                            TableRow tableRow = new TableRow(getActivity());
                            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

                            for (int j = 0; j < wholeField.size(); j++) {
                                final TextView text = new TextView(getActivity());
                                text.setText(c.getString(j + 1));
                                text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                                tableRow.addView(text);
                            }
                            resultset.addView(tableRow);
                        }//end while
                    }

                    handler.close();

                }catch(Exception e){
                    Toast toast = Toast.makeText(getActivity(),"다시 잘해보시게", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }


}
