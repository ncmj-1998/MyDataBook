package sg.edu.rp.webservices.mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;

    ArrayList<MenuItem> menuList;

    public MenuAdapter(Context context, int resource,
                         ArrayList<MenuItem> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        menuList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) parent_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        View rowView = inflater.inflate(layout_id, parent, false);


        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        ImageView ivIcon = (ImageView) rowView.findViewById(R.id.ivIcon);


        MenuItem currentItem = menuList.get(position);


        tvName.setText(currentItem.getName());
        ivIcon.setImageResource(currentItem.getIcon());


        return rowView;
    }
}

