package rabbitescape.ui.android;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.artificialworlds.rabbitescape.R;

import rabbitescape.engine.config.TapTimer;
import rabbitescape.engine.menu.LevelMenuItem;
import rabbitescape.engine.menu.Menu;
import rabbitescape.engine.menu.MenuItem;

import static rabbitescape.engine.i18n.Translation.t;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder>
{
    private final MenuItem[] items;
    private final AndroidMenuActivity menuActivity;
    private final View.OnClickListener mOnClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            view.setOnClickListener(mOnClickListener);
        }
    }

    public MenuListAdapter( AndroidMenuActivity menuActivity, Menu menu, View.OnClickListener mOnClickListener)
    {
        this.menuActivity = menuActivity;
        items = menu.items;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item_1, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MenuItem item = items[ position ];

        holder.title.setText( t( item.name, item.nameParams ) );
        holder.title.setTypeface(
                null,
                menuActivity.selectedItemPosition == position ? Typeface.BOLD : Typeface.NORMAL
        );
        holder.title.setEnabled( item.enabled || TapTimer.matched );
        if (item instanceof LevelMenuItem) {
            holder.icon.setVisibility(View.VISIBLE);
            holder.icon.setEnabled( item.enabled || TapTimer.matched );
            holder.icon.setImageResource(item.enabled || TapTimer.matched  ? R.drawable.lock_open_24px : R.drawable.lock24px);
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
