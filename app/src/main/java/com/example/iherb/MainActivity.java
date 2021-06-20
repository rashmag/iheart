package com.example.iherb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.iherb.Fragments.ArticlesFragment;
import com.example.iherb.Fragments.CartFragment;
import com.example.iherb.Fragments.CartProductFragment;
import com.example.iherb.Fragments.PersonInformation;
import com.example.iherb.Fragments.PulsFragment;
import com.example.iherb.Fragments.ShoppingFragment;
import com.example.iherb.Fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameContainer;
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));
        init();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace
                (R.id.frameContainer, new ArticlesFragment()).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_house:
                        if (fragmentManager.findFragmentByTag("ArticlesFragment") != null) {
                            //if the fragment exists, show it.
                            fragmentManager.beginTransaction().show(fragmentManager.
                                    findFragmentByTag("ArticlesFragment"))
                                    .commit();
                        } else {
                            //if the fragment does not exist, add it to fragment manager.
                            fragmentManager.beginTransaction().add(R.id.frameContainer,
                                    new ArticlesFragment(),
                                    "ArticlesFragment").commit();
                        }
                        hideTwo(fragmentManager);
                        hideThree(fragmentManager);
                        hideFour(fragmentManager);
                        hideCart(fragmentManager);
                        hidePersonFragment(fragmentManager);
//                        selectedFragment = new ArticlesFragment();
                        break;
                    case R.id.navigation_activity:
                        if (fragmentManager.findFragmentByTag("PulsFragment") != null) {
                            //if the fragment exists, show it.
                            fragmentManager.beginTransaction().show(fragmentManager.
                                    findFragmentByTag("PulsFragment"))
                                    .commit();
                        } else {
                            //if the fragment does not exist, add it to fragment manager.
                            fragmentManager.beginTransaction().add(R.id.frameContainer,
                                    new PulsFragment(),
                                    "PulsFragment").commit();
                        }
                        hideOne(fragmentManager);
                        hideThree(fragmentManager);
                        hideFour(fragmentManager);
                        hideCart(fragmentManager);
                        hidePersonFragment(fragmentManager);
//                        selectedFragment = new PulsFragment();
                        break;
                    case R.id.navigation_shopping:
                        if (fragmentManager.findFragmentByTag("ShoppingFragment") != null) {
                            //if the fragment exists, show it.
                            fragmentManager.beginTransaction().show(fragmentManager.
                                    findFragmentByTag("ShoppingFragment"))
                                    .commit();
                        } else {
                            //if the fragment does not exist, add it to fragment manager.
                            fragmentManager.beginTransaction().add(R.id.frameContainer,
                                    new ShoppingFragment(),
                                    "ShoppingFragment").commit();
                        }
                        hideOne(fragmentManager);
                        hideFour(fragmentManager);
                        hideTwo(fragmentManager);
                        hideCart(fragmentManager);
                        hidePersonFragment(fragmentManager);
//                        selectedFragment = new ShoppingFragment();
                        break;
                    case R.id.navigation_user:
//                        startActivity(new Intent(MainActivity.this, EntranceAndRegister.class));
                        if (fragmentManager.findFragmentByTag("UserFragment") != null) {
                            //if the fragment exists, show it.
                            fragmentManager.beginTransaction().show(fragmentManager.
                                    findFragmentByTag("UserFragment"))
                                    .commit();
                        } else {
                            //if the fragment does not exist, add it to fragment manager.
                            fragmentManager.beginTransaction().add(R.id.frameContainer,
                                    new UserFragment(),
                                    "UserFragment").commit();
                        }
                        hideOne(fragmentManager);
                        hideThree(fragmentManager);
                        hideTwo(fragmentManager);
                        hideCart(fragmentManager);
                        hidePersonFragment(fragmentManager);
                        selectedFragment = new UserFragment();
                        break;
                }
//                    fragmentManager.beginTransaction().replace
//                            (R.id.frameContainer,selectedFragment).commit();
                return true;
            }
        });
    }

    public void hideOne(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("ArticlesFragment") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ArticlesFragment")).commit();
        }

    }

    public void hideTwo(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("PulsFragment") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("PulsFragment")).commit();
        }
    }

    public void hideThree(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("ShoppingFragment") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("ShoppingFragment")).commit();
        }
    }

    public void hideFour(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("UserFragment") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("UserFragment")).commit();
        }
    }
    public void hideCart(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("CartFragment") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("CartFragment")).commit();
        }
    }
    public void hidePersonFragment(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag("PersonInformation") != null) {
            //if the other fragment is visible, hide it.
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("PersonInformation")).commit();
        }
    }

    public void cartProductFragment(String imgUrl, String name, String price) {
        hideOne(fragmentManager);
        hideFour(fragmentManager);
        hideTwo(fragmentManager);
        hideThree(fragmentManager);
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new CartProductFragment(imgUrl, name, price),
                    "CartFragment").commit();
//        fragmentManager.beginTransaction().addToBackStack(null).
//                replace(R.id.frameContainer,
//                        new CartFragment(imgUrl, name, price)).commit();
    }
//    public void personFragment() {
//        hideOne(fragmentManager);
//        hideFour(fragmentManager);
//        hideTwo(fragmentManager);
//        hideThree(fragmentManager);
//        if (fragmentManager.findFragmentByTag("PersonInformation") != null) {
//            fragmentManager.beginTransaction().show(fragmentManager.
//                    findFragmentByTag("PersonInformation"))
//                    .commit();
//        } else {
//            fragmentManager.beginTransaction().add(R.id.frameContainer,
//                    new PersonInformation(),
//                    "PersonInformation").commit();
//        }
//    }
    public void stepFragment2() {
        hideOne(fragmentManager);
        hideFour(fragmentManager);
        hideTwo(fragmentManager);
        hideThree(fragmentManager);
        if (fragmentManager.findFragmentByTag("PersonInformation") != null) {
            //if the fragment exists, show it.
            fragmentManager.beginTransaction().show(fragmentManager.
                    findFragmentByTag("PersonInformation"))
                    .commit();
        } else {
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new PersonInformation(),
                    "PersonInformation").commit();
        }
//        fragmentManager.beginTransaction().addToBackStack(null).
//                replace(R.id.frameContainer,
//                        new ShoppingFragment()).commit();
    }

    public void shopFragment() {
        hideOne(fragmentManager);
        hideFour(fragmentManager);
        hideCart(fragmentManager);
        hideThree(fragmentManager);
        if (fragmentManager.findFragmentByTag("ShoppingFragment") != null) {
            //if the fragment exists, show it.
            fragmentManager.beginTransaction().show(fragmentManager.
                    findFragmentByTag("ShoppingFragment"))
                    .commit();
        } else {
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new ShoppingFragment(),
                    "ShoppingFragment").commit();
        }
//        fragmentManager.beginTransaction().addToBackStack(null).
//                replace(R.id.frameContainer,
//                        new PulsFragment()).commit();
    }
    public void stepFragment() {
        hideOne(fragmentManager);
        hideFour(fragmentManager);
        hideCart(fragmentManager);
        hideThree(fragmentManager);
        if (fragmentManager.findFragmentByTag("PulsFragment") != null) {
            //if the fragment exists, show it.
            fragmentManager.beginTransaction().show(fragmentManager.
                    findFragmentByTag("PulsFragment"))
                    .commit();
        } else {
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new PulsFragment(),
                    "PulsFragment").commit();
        }
//        fragmentManager.beginTransaction().addToBackStack(null).
//                replace(R.id.frameContainer,
//                        new ShoppingFragment()).commit();
    }


    public void cartFragment() {
        hideOne(fragmentManager);
        hideFour(fragmentManager);
        hideTwo(fragmentManager);
        hideCart(fragmentManager);
        if (fragmentManager.findFragmentByTag("CartFragment") != null) {
            //if the fragment exists, show it.
            fragmentManager.beginTransaction().show(fragmentManager.
                    findFragmentByTag("CartFragment"))
                    .commit();
        } else {
            //if the fragment does not exist, add it to fragment manager.
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new CartFragment(),
                    "CartFragment").commit();
        }
//        fragmentManager.beginTransaction().addToBackStack(null).
//                replace(R.id.frameContainer,
//                        new ShoppingFragment()).commit();
    }

    private void init() {
        frameContainer = findViewById(R.id.frameContainer);
        bottomNav = findViewById(R.id.bottomNav);
    }
}