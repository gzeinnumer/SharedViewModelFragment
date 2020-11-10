# SharedViewModelFragment
 pengganti bundle
 
 - [ViewBinding](https://github.com/gzeinnumer/ViewBindingExample)
 - [SharedViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=id#sharing)

 - `Gradel`
 ```gradle
def lifecycle_version = "2.2.0"
implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
```

- `SharedVM.java`
```java
public class SharedVM extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<>();

    void select(String item) {
        selected.setValue(item);
    }

    LiveData<String> getSelected() {
        return selected;
    }
}
```

- `MainActivity.java`
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new SecondFragment();
        fragmentTransaction.replace(R.id.fr_2, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
```

- `FirstFragment.java`
```java
public class FirstFragment extends Fragment {

    private SharedVM model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(getActivity()).get(SharedVM.class);
    }

    private FragmentFirstBinding binding;
    public FirstFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnFr1.setOnClickListener(v -> model.select(binding.tvFr1.getText().toString()));
    }
}
```

- `SecondFragment.java`
```java
public class SecondFragment extends Fragment {

    FragmentSecondBinding binding;
    public SecondFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedVM model = new ViewModelProvider(getActivity()).get(SharedVM.class);
        model.getSelected().observe(getViewLifecycleOwner(), s -> {
            binding.tvFr2.setText(s);
        });
    }
}
```
---

```
Copyright 2020 M. Fadli Zein
```
