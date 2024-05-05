package iuh.three_fool_guys.badminton_booking_app.authentication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import iuh.three_fool_guys.badminton_booking_app.R;

public class AuthDialog extends DialogFragment {

        private Context context;

        public AuthDialog(@NonNull Context context) {
            this.context = context;
        }



        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.activity_login);

            TabLayout tabLayout = dialog.findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Login"));
            tabLayout.addTab(tabLayout.newTab().setText("Register"));
            ViewPager2 viewPager = dialog.findViewById(R.id.pager);
            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(position == 0 ? "Login" : "Register")).attach();

            AuthAdapter adapter = new AuthAdapter(context, getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);

            return dialog;
        }
}
