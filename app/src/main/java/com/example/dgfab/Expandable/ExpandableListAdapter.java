package com.example.dgfab.Expandable;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dgfab.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    View ExpandView;
    private Context _context;
    LayoutInflater infalInflater;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<BasicInfo>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<BasicInfo>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        //      Log.e("Expanded view" , "groupPosition"+groupPosition);
//        if(groupPosition)
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

//        final String childText =  getChild(groupPosition, childPosition);
        final BasicInfo BasicInfo = (BasicInfo) getChild(groupPosition, childPosition);
        Log.e("childPosition", "is " + childPosition);
        Log.e("groupPosition", "is groupPosition " + groupPosition);
        int itemType = getChildType(groupPosition, childPosition);
//        this.infalInflater = LayoutInflater.from(parent.getContext());
//        convertView.re
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (("Basic Information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Basic", Toast.LENGTH_SHORT).show();
            convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
            convertView.requestFocus();
            //update your views here
        } else if (("Trade Information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Trade", Toast.LENGTH_SHORT).show();
            convertView = infalInflater.inflate(R.layout.tradeinfos, null);
            convertView.requestFocus();
            //update your views here
        } else if (("Logistics information").equals(_listDataHeader.get(groupPosition))) {
            Toast.makeText(_context, "Trade", Toast.LENGTH_SHORT).show();
            convertView = infalInflater.inflate(R.layout.logisticinfo, null);
            convertView.requestFocus();
            //update your views here
        }
//            switch (itemType) {
//                case 0:
//                    Toast.makeText(_context, "we are at"+itemType, Toast.LENGTH_SHORT).show();
//                    convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
//                    break;
//                case 1:
//                    Toast.makeText(_context, "we are at"+itemType, Toast.LENGTH_SHORT).show();
//                    convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                    break;
//            }
//            if(groupPosition==0) {
//                Toast.makeText(_context, "at"+groupPosition, Toast.LENGTH_SHORT).show();
//                 infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.basicinfolistitem, null);
//                notifyDataSetChanged();
//            }
//            if(groupPosition ==1)
//            {
//                Toast.makeText(_context, "at"+groupPosition, Toast.LENGTH_SHORT).show();
//                 infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                notifyDataSetChanged();
//            }

//        EditText Proname = convertView.findViewById(R.id.pronames);
//        //  Proname.getText().clear();
//        Proname.setHint("Product Name");

//        TextView txtListChild = (TextView) convertView
//                .findViewById(R.id.lblListItem);
//
//        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        this.ExpandView = convertView;
        //   Log.e("groupPosition", "is " + groupPosition);
        if (convertView == null) {
//            if(headerTitle.equals("Basic Information")) {
            Toast.makeText(_context, "BAsic", Toast.LENGTH_SHORT).show();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.basicinfogroup, null);

//            }
//            else {
//                Toast.makeText(_context, "Trading", Toast.LENGTH_SHORT).show();
//                LayoutInflater infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.tradeinfos, null);
//                TextView lblListHeader = (TextView) convertView
//                        .findViewById(R.id.lblListHeader);
//
//                lblListHeader.setTypeface(null, Typeface.BOLD);
//                lblListHeader.setText(headerTitle);
//            }
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void ChangeIconUp() {
        Toast.makeText(_context, "I called", Toast.LENGTH_SHORT).show();
        ImageView expaicon = ExpandView.findViewById(R.id.expaicon);
        expaicon.setImageResource(R.drawable.expandup);
        //  expaicon.setVisibility(View.GONE);
        //notify();
    }

    public void ChangeIconDown() {
        Toast.makeText(_context, "I called", Toast.LENGTH_SHORT).show();
        ImageView expaicon = ExpandView.findViewById(R.id.expaicon);
        expaicon.setImageResource(R.drawable.expanddown);
        //  expaicon.setVisibility(View.GONE);
        //notify();
    }
}
