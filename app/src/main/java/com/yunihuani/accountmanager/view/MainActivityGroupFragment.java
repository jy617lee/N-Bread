package com.yunihuani.accountmanager.view;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yunihuani.accountmanager.R;
import com.yunihuani.accountmanager.system.Account;
import com.yunihuani.accountmanager.system.Group;

import java.util.ArrayList;
import java.util.List;

public class MainActivityGroupFragment extends Fragment {

    private ArrayList<Group> groups;
    private GroupListAdapter mListAdapter;

    public MainActivityGroupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_group, container, false);
        ListView listview = (ListView) rootView.findViewById(R.id.main_group_listview);

        groups = new ArrayList<Group>();
        mListAdapter = new GroupListAdapter(getActivity(), R.id.main_group_listview, groups);
        listview.setAdapter(mListAdapter);
        getGroupData();

        ((Button) rootView.findViewById(R.id.main_group_btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), GroupDetailActivity.class));
            }
        });

        return rootView;
    }

    private void addDummyData() {
        MainActivity.mDbManager.GroupTable.create(new Group(1, "Group1", new Account()));
        MainActivity.mDbManager.GroupTable.create(new Group(2, "Group2", new Account()));
        MainActivity.mDbManager.GroupTable.create(new Group(3, "Group3", new Account()));
    }

    private void getGroupData() {
        mListAdapter.setList(MainActivity.mDbManager.GroupTable.fetchList());
    }

    private class GroupListAdapter extends ArrayAdapter<Group> {
        private List<Group> items;
        public GroupListAdapter(Context context, int resourceId, ArrayList<Group> groupList) {
            super(context, resourceId, groupList);
            items = groupList;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(android.R.layout.simple_list_item_1, null);
            }
            Group group = items.get(position);
            if (group != null) {
                TextView top = (TextView) v.findViewById(android.R.id.text1);
                if(top != null) {
                    top.setText(group.getName());
                }
            }
            return v;
        }
        public void setList(final List<Group> list) {
            items.clear();
            for(int i = 0; i < list.size(); i++) {
                items.add(list.get(i));
            }
            this.notifyDataSetChanged();
        }
    }
}
