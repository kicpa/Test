package kms.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        //DB 관련 부분
/*
        DBmanager testHelper = new DBmanager(this,"test.db",null,1);
        SQLiteDatabase db = testHelper.getWritableDatabase();
        db.execSQL("insert into test values(null,'피카츄');");
        db.execSQL("insert into test values(null,'라이츄');");
        testHelper.onUpgrade(db,1,2);//업그레이드를 통한 초기화
        db.close();

        // 데이터베이스 연동
        //DBmanager dbdb = new DBmanager(this,"example.db",null,1);
        SQLiteHandler handler = SQLiteHandler.open(getApplicationContext());

        // 데이터 저장
        //handler.insert("홍길동", 20, "서울");
       // handler.insert("이순신", 44, "전라");
        //handler.close();

        // 데이터 수정
       // handler.update("홍길동", 55);
       // handler.close();

        // 데이터 삭제
       // handler.delete("이순신");
        //handler.close();

        // 데이터 검색
        Cursor c = handler.select();
        startManagingCursor(c);
        while(c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));
            String data = _id+" "+name+" "+age+" "+address;
            Log.i("xxx", data);  //Log.i(태그, 출력텍스트) --> ddms 의 log 창에서 확인가능

        }//end while

        handler.close();*/


    }
    //public void onClick(View view){Toast.makeText(this, "asdfadf", Toast.LENGTH_LONG).show();}

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(position==0){
            fragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(position+1))
                .commit();
        }else if(position==1){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance(position+1))
                    .commit();
        }else if(position==2){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, EARFragment.newInstance(position+1))
                    .commit();
        }else if(position==3){
            startActivity( new Intent(this,MapFragment.class) );

        }
        //2번째 + 처음 실행 후에는 1번째
    }

    public void onSectionAttached(int number) {
        switch (number) {//3번째 + 처음 실행 후에는 2번째
            case 1:
                mTitle = getString(R.string.title_section0);
                break;
            case 2:
                mTitle = getString(R.string.title_section1);
                break;
            case 3:
                mTitle = getString(R.string.title_section2);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}