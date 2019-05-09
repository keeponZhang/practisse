package com.Sidebar.intentseconddemo.sidebardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.Sidebar.intentseconddemo.sidebardemo.domain.Person;
import com.Sidebar.intentseconddemo.sidebardemo.utils.DensityUtil;
import com.Sidebar.intentseconddemo.sidebardemo.view.Sidebar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Sidebar mSidebar;
    private ListView mListView;

    private List<Person> mPersonList = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        addData();
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(this);
    }

    private void initView() {
        mSidebar = (Sidebar) findViewById(R.id.es_for_search);
        mListView = (ListView) findViewById(R.id.lv_search);
        mSidebar.setListView(mListView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(MainActivity.this, mPersonList.get(position).toString(), Toast.LENGTH_SHORT).show();

    }


    /**
     * 列表适配器
     */
    private class MyAdapter extends BaseAdapter implements SectionIndexer {

        private SparseIntArray positionOfSection;
        private SparseIntArray sectionOfPosition;
        private List list;

        @Override
        public int getCount() {
            return mPersonList.size();
        }

        @Override
        public Object getItem(int position) {
            return mPersonList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler holder;
            Person person = mPersonList.get(position);
            if (convertView == null) {
                holder = new ViewHodler();
                convertView = View.inflate(MainActivity.this, R.layout.list_view_person, null);
                holder.mtext = (TextView) convertView.findViewById(R.id.tv_text_name);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_avter);
                holder.mTextGrapheme = (TextView) convertView.findViewById(R.id.tv_grapheme);
                convertView.setTag(holder);
            } else {
                holder = (ViewHodler) convertView.getTag();
            }

            holder.mtext.setText(person.getName());
            holder.mImageView.setImageResource(R.drawable.ic_launcher);
            String header = person.getInitial();

            /* 控制显示头一个字母 */
            if (position == 0 || header != null && !header.equals(mPersonList.get(position - 1).getInitial())) {
                if (TextUtils.isEmpty(header)) {
                    holder.mTextGrapheme.setVisibility(View.GONE);
                } else {
                    holder.mTextGrapheme.setVisibility(View.VISIBLE);
                    holder.mTextGrapheme.setText(header);
                }
            } else {
                holder.mTextGrapheme.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHodler {
            TextView mtext;
            ImageView mImageView;
            TextView mTextGrapheme;
        }

        /**
         * 实现 SectionIndexer 接口
         *
         * @return
         */
        @Override
        public Object[] getSections() {
            positionOfSection = new SparseIntArray();
            sectionOfPosition = new SparseIntArray();
            positionOfSection.put(0, 0);
            sectionOfPosition.put(0, 0);
            list = new ArrayList<String>();
            list.add("sousuo");
            int count = mPersonList.size();
            for (int i = 0; i < count-1; i++) {
                int section = list.size() - 1;
                String letter = mPersonList.get(i).getInitial();
           //     String letter = getIntialLetterString(mPersonList.get(i).getName());

                if (list.get(section) != null && !list.get(section).equals(letter)) {
                    list.add(letter);
                    section++;
                    positionOfSection.put(section, i);
                }
                sectionOfPosition.put(i, section);
            }
            Object[] objects = list.toArray(new String[list.size()]);
	        for (Object object : objects) {
		        Log.e(TAG, "getSections:"+object);
	        }
            Log.e(TAG, "getSections:positionOfSection=="+positionOfSection);
            Log.e(TAG, "getSections:sectionOfPosition=="+sectionOfPosition);

            return objects;
        }

        private static final String TAG = "MyAdapter";
        /**
         * 实现 SectionIndexer 接口
         *
         * @return
         */
        @Override
        public int getPositionForSection(int sectionIndex) {
            return positionOfSection.get(sectionIndex);
        }

        /**
         * 实现 SectionIndexer 接口
         *
         * @return
         */
        @Override
        public int getSectionForPosition(int position) {
            return sectionOfPosition.get(position);
        }
    }


    public void addData() {
        Person person1 = new Person();
        person1.setName("Long2");
        getInitialLetter(person1.getName(), person1);
        mPersonList.add(person1);

        Person person2 = new Person();
        person2.setName("Wang2");
        getInitialLetter(person2.getName(), person2);
        mPersonList.add(person2);

        Person person3 = new Person();
        person3.setName("Zi");
        getInitialLetter(person3.getName(), person3);
        mPersonList.add(person3);

        Person person4 = new Person();
        person4.setName("王");
        getInitialLetter(person4.getName(), person4);
        mPersonList.add(person4);

        Person person5 = new Person();
        person5.setName("景");
        getInitialLetter(person5.getName(), person5);
        mPersonList.add(person5);


        Person person6 = new Person();
        person6.setName("黄");
        getInitialLetter(person6.getName(), person6);
        mPersonList.add(person6);

        Person person7 = new Person();
        person7.setName("龙");
        getInitialLetter(person7.getName(), person7);
        mPersonList.add(person7);

        Person person9 = new Person();
        person9.setName("立zzz");
        getInitialLetter(person9.getName(), person9);
        mPersonList.add(person9);

        Person person10 = new Person();
        person10.setName("数码宝贝");
        getInitialLetter(person10.getName(), person10);
        mPersonList.add(person10);

        Person person11 = new Person();
        person11.setName("123456789");
        getInitialLetter(person11.getName(), person11);
        mPersonList.add(person11);

        Person person12 = new Person();
        person12.setName("澳大利亚");
        getInitialLetter(person12.getName(), person12);
        mPersonList.add(person12);

        Person person13 = new Person();
        person13.setName("美国");
        getInitialLetter(person13.getName(), person13);
        mPersonList.add(person13);

        for (int i = 10; i < 20; i++) {
            Person persona = new Person();
            persona.setName("立");
            getInitialLetter(persona.getName(), persona);
            mPersonList.add(persona);
        }
        // 对list进行排序
        Collections.sort(mPersonList, new Comparator<Person>() {

            @Override
            public int compare(Person lhs, Person rhs) {
                if (lhs.getInitial().equals(rhs.getInitial())) {

                    return lhs.getName().compareTo(rhs.getName());
                } else {
                    if ("#".equals(lhs.getInitial())) {
                        return 1;
                    } else if ("#".equals(rhs.getInitial())) {
                        return -1;
                    }
                    return lhs.getInitial().compareTo(rhs.getInitial());
                }

            }
        });
    }

    /**
     * 获取首字母 ！
     *
     * @param headerName
     * @param user
     */
    public static void getInitialLetter(String headerName, Person user) {
        if (Character.isDigit(headerName.charAt(0))) {
            user.setInitial("#");
        } else {
            user.setInitial(DensityUtil.getPingYin(headerName));
            char header = user.getInitial().toLowerCase().charAt(0);
            if (header < 'a' || header > 'z') {
                user.setInitial("#");
            }
        }
    }

    /**
     * 获取首字母 ！
     * 返回 首字母
     */
    public static String getIntialLetterString(String s) {

        if (Character.isDigit(s.charAt(0))) {
            return "#";
        }
        String sIntial = DensityUtil.getPingYin(s);

        char header = sIntial.toLowerCase().charAt(0);
        if (header < 'a' || header > 'z') {
                return "#";
          }
        return sIntial;
    }
}
