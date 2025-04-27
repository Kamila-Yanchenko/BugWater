package com.example.bugwater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChartsActivity extends AppCompatActivity {

    private CustomLineChart monthlyChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_charts);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ініціалізація графіка
        monthlyChart = findViewById(R.id.monthlyChart);
        setupMonthlyChart();

        // Налаштування нижньої навігації
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_map) {
                startActivity(new Intent(this, MapsActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_chart) {
                Toast.makeText(this, "Ви вже на графіках", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void setupMonthlyChart() {
        // Усереднені середньомісячні витрати за 1914–2007 роки
        float[] averageMonthlyFlows = calculateAverageMonthlyFlows();
        monthlyChart.setData(averageMonthlyFlows);
    }

    private float[] calculateAverageMonthlyFlows() {
        // Дані для с. Олександрівка (1914–2007 роки)
        float[][] monthlyFlows = {
                {44.6f, 69.1f, 114f, 77.3f, 69.1f, 59.4f, 58.7f, 44.7f, 31.9f, 47.4f, 49.2f, 48.7f},
                {95.6f, 160f, 254f, 161f, 46.9f, 29.7f, 25.7f, 20.9f, 21.8f, 22.5f, 43f, 47.8f},
                {37.3f, 58.7f, 159f, 111f, 80.2f, 124f, 54.3f, 37f, 24.7f, 33.1f, 69.9f, 81.1f},
                {42.3f, 52.6f, 435f, 532f, 84.1f, 45.5f, 43.4f, 41.3f, 36.1f, 28.4f, 40.1f, 32.6f},
                {35.8f, 62.2f, 91.3f, 53.2f, 34.7f, 28.5f, 90.1f, 81.1f, 50.8f, 46.8f, 44.1f, 45f},
                {51f, 33.7f, 182f, 150f, 103f, 112f, 77.8f, 59.6f, 38.8f, 30.1f, 73.5f, 106f},
                {63.1f, 34.9f, 232f, 168f, 57.7f, 45f, 79.1f, 24f, 23.4f, 22.4f, 18.4f, 15.7f},
                {19.7f, 10.6f, 61.7f, 49.9f, 44.8f, 42.3f, 29f, 16.5f, 15.7f, 15.2f, 17.8f, 13.4f},
                {22.5f, 19f, 743f, 216f, 84.9f, 75.4f, 37.3f, 26.6f, 41.6f, 65.8f, 105f, 45.3f},
                {24f, 30.6f, 523f, 155f, 108f, 86.1f, 76.5f, 45.5f, 39.8f, 53.8f, 51.7f, 86.3f},
                {91.3f, 61.1f, 156f, 574f, 101f, 61.8f, 48.7f, 63.9f, 38.6f, 33.7f, 36.7f, 47.3f},
                {37.7f, 69.5f, 86.8f, 51.2f, 53.7f, 35.2f, 42.7f, 53.5f, 76.7f, 45.3f, 47.2f, 35.6f},
                {47.9f, 78.3f, 369f, 205f, 86.3f, 84.9f, 60f, 57.8f, 35.7f, 35.6f, 43.8f, 48.9f},
                {53.9f, 50.2f, 208f, 96.5f, 63.8f, 40.5f, 29.6f, 30.6f, 22.1f, 22.4f, 27f, 33.7f},
                {24.3f, 144f, 145f, 240f, 60f, 44.8f, 33.6f, 14.2f, 14.5f, 16.4f, 24.9f, 68.5f},
                {33.9f, 21.9f, 190f, 768f, 130f, 44.5f, 48.4f, 28.9f, 17.1f, 18.6f, 25.7f, 33.4f},
                {35.2f, 82.1f, 96.9f, 94f, 100f, 58.2f, 23.4f, 15.5f, 14.5f, 18.4f, 31.3f, 33.3f},
                {24.9f, 29f, 242f, 432f, 114f, 78.7f, 29.4f, 55.1f, 34.6f, 44f, 66.4f, 48.8f},
                {109f, 78.1f, 132f, 1160f, 164f, 143f, 89.6f, 54.9f, 30.1f, 47.5f, 53.7f, 56.5f},
                {65.3f, 48f, 336f, 151f, 88.1f, 106f, 132f, 68.1f, 73f, 78.8f, 91.4f, 57.6f},
                {50.6f, 115f, 384f, 92.8f, 30.8f, 21.4f, 31.5f, 41.4f, 39.2f, 33.8f, 47.2f, 36.9f},
                {34.8f, 73.8f, 479f, 141f, 72.6f, 36.4f, 20.7f, 19.1f, 25.1f, 22.5f, 25.2f, 29.9f},
                {52f, 46.5f, 61.8f, 52.6f, 25.2f, 17.6f, 13f, 11.3f, 23.4f, 50.8f, 54.4f, 71.7f},
                {60.2f, 38.6f, 565f, 115f, 158f, 38.3f, 21f, 62.8f, 61.9f, 69.4f, 64.7f, 81.8f},
                {82f, 92.4f, 199f, 90f, 72.3f, 47.6f, 27.5f, 24f, 43.1f, 35f, 49.7f, 46.5f},
                {44f, 138f, 116f, 83.2f, 40.5f, 29.5f, 26f, 31.9f, 26.8f, 36.8f, 59.1f, 74.5f},
                {38.6f, 25.3f, 570f, 829f, 104f, 46.1f, 38f, 43.4f, 58.6f, 56.5f, 82f, 67.3f},
                {38.7f, 571f, 550f, 379f, 133f, 82.2f, 76f, 60f, 109f, 120f, 115f, 85f},
                {76.5f, 64f, 176f, 683f, 171f, 93.8f, 89.2f, 56.8f, 48.9f, 55.7f, 57.5f, 57.5f},
                {55.7f, 48.9f, 234f, 113f, 55.4f, 47f, 53.3f, 52f, 43.4f, 45.2f, 56.2f, 67.2f},
                {64.6f, 119f, 210f, 157f, 78f, 44.6f, 44.7f, 40f, 26.7f, 30.5f, 48.3f, 70.9f},
                {29.5f, 29.9f, 582f, 142f, 57.7f, 33.6f, 25.8f, 21.8f, 21.6f, 31.4f, 32.6f, 27.5f},
                {70.9f, 76.6f, 296f, 90.6f, 31.8f, 21.7f, 17.8f, 13.4f, 12.8f, 26.1f, 35.5f, 35.3f},
                {13.3f, 26.9f, 781f, 186f, 33.5f, 26.4f, 22.2f, 71.8f, 112f, 54.5f, 81.4f, 125f},
                {261f, 215f, 163f, 112f, 43.2f, 168f, 195f, 72.3f, 45.2f, 49.8f, 53.7f, 37f},
                {34.3f, 108f, 242f, 144f, 44.7f, 77.3f, 112f, 124f, 71.8f, 51f, 56.9f, 88.7f},
                {61f, 150f, 129f, 86.2f, 39.9f, 23f, 18.4f, 24.7f, 21.4f, 35.5f, 59.6f, 60.2f},
                {44.3f, 49.1f, 479f, 96.9f, 49.3f, 25.6f, 15.1f, 31.6f, 15.3f, 27.3f, 29.6f, 28.8f},
                {36.7f, 52.1f, 46.6f, 381f, 45.2f, 43f, 37.7f, 19.5f, 17.6f, 32.3f, 62.1f, 63.6f},
                {57.9f, 102f, 342f, 152f, 119f, 89.5f, 41.6f, 28.5f, 24.7f, 32.4f, 29.2f, 37.9f},
                {22.2f, 11.7f, 58.5f, 68.8f, 48.1f, 50.6f, 44.7f, 34.5f, 42.1f, 44.1f, 40.2f, 45.4f},
                {73.7f, 57.6f, 148f, 103f, 52.7f, 55.5f, 60.9f, 91.6f, 58.5f, 68.6f, 67.6f, 59.2f},
                {84.4f, 202f, 187f, 723f, 57.3f, 42.6f, 30.3f, 28.4f, 43.5f, 40.4f, 39.7f, 47.6f},
                {47.5f, 129f, 85.9f, 56.4f, 44.8f, 36.3f, 21.2f, 15.4f, 19.6f, 32.1f, 27.8f, 24f},
                {24.4f, 159f, 151f, 107f, 42.5f, 42f, 33.8f, 44.7f, 86.5f, 63f, 77.2f, 52.7f},
                {74.6f, 48.4f, 81.1f, 61.2f, 44.4f, 36.8f, 12.7f, 11.5f, 10.6f, 15.9f, 27.7f, 35.6f},
                {81.1f, 257f, 276f, 91.8f, 53.6f, 40.7f, 29.9f, 54.2f, 47.5f, 54.5f, 83.4f, 149f},
                {120f, 87.8f, 122f, 63.1f, 46.3f, 51.7f, 26.4f, 35.3f, 34.4f, 34.2f, 28.5f, 30.4f},
                {33.8f, 39.9f, 126f, 357f, 77.6f, 51.2f, 82.2f, 56f, 38f, 51f, 46.9f, 56.2f},
                {26.2f, 106f, 337f, 411f, 70.6f, 42.7f, 31.1f, 17.4f, 15.9f, 20.3f, 24.4f, 22.9f},
                {27.1f, 25.5f, 53.8f, 108f, 44.5f, 31.2f, 28.9f, 30.9f, 26.6f, 61.8f, 54.3f, 58.3f},
                {59.5f, 55.9f, 332f, 181f, 84.1f, 75.9f, 64.1f, 66.8f, 46.4f, 51.1f, 65.6f, 71.3f},
                {88.2f, 149f, 219f, 156f, 59f, 57.9f, 53.9f, 58.9f, 82.9f, 60.5f, 93f, 97.9f},
                {77f, 85.1f, 404f, 188f, 74.9f, 84.2f, 49.5f, 38.4f, 31.9f, 40.6f, 47f, 50f},
                {60.8f, 78.2f, 372f, 166f, 50.4f, 28.7f, 25.3f, 51.6f, 55f, 165f, 88.1f, 64.8f},
                {37f, 47.6f, 268f, 588f, 98.8f, 82.1f, 156f, 87.1f, 76f, 68.9f, 59.6f, 69.6f},
                {73.3f, 159f, 580f, 206f, 147f, 148f, 74.6f, 77.5f, 81f, 77.7f, 100f, 120f},
                {108f, 126f, 222f, 157f, 90.8f, 49.1f, 142f, 49.8f, 105f, 106f, 84.3f, 114f},
                {81.9f, 69.6f, 125f, 81.2f, 75.9f, 34.6f, 100f, 83.6f, 62f, 89.1f, 90.3f, 76.8f},
                {44.2f, 116f, 269f, 204f, 67f, 97f, 67.5f, 40.1f, 48.1f, 77f, 55.4f, 38f},
                {51.8f, 104f, 72.7f, 38.2f, 46.4f, 44.7f, 125f, 100f, 62.8f, 117f, 164f, 104f},
                {121f, 87.6f, 86.5f, 75.2f, 93.1f, 106f, 43.6f, 33.9f, 36.8f, 46.4f, 48.2f, 45.2f},
                {53.6f, 57.6f, 192f, 233f, 74.5f, 41.6f, 26f, 37.2f, 78.3f, 95.5f, 82.3f, 84.2f},
                {46.2f, 447f, 193f, 136f, 97.5f, 65.9f, 73.2f, 71.3f, 77.2f, 82.4f, 78.6f, 66.3f},
                {44.4f, 43.2f, 568f, 140f, 72.2f, 50.1f, 138f, 104f, 90.1f, 115f, 83.3f, 72.4f},
                {211f, 362f, 384f, 297f, 78.7f, 40.6f, 37.9f, 45.4f, 47f, 66.3f, 85.2f, 87.7f},
                {104f, 66.2f, 249f, 942f, 129f, 112f, 158f, 113f, 85.5f, 118f, 123f, 177f},
                {157f, 166f, 259f, 157f, 129f, 83.4f, 102f, 66.7f, 86.6f, 123f, 138f, 155f},
                {167f, 87.8f, 138f, 135f, 103f, 64.3f, 211f, 68.6f, 64.4f, 83.2f, 74.9f, 84.2f},
                {83.7f, 90.4f, 97f, 81.4f, 83.5f, 55.1f, 68.3f, 86.6f, 74.7f, 73.1f, 63.8f, 57.8f},
                {77.2f, 109f, 148f, 167f, 79.4f, 105f, 91.2f, 60.5f, 58.2f, 117f, 81.5f, 78.6f},
                {92.2f, 76.7f, 293f, 365f, 104f, 106f, 133f, 74.5f, 69.1f, 87.8f, 89.6f, 89f},
                {104f, 94f, 219f, 161f, 55.7f, 35.8f, 29.6f, 29f, 34.2f, 51.1f, 46f, 56f},
                {51.4f, 65.1f, 108f, 237f, 78.2f, 68.9f, 39.3f, 46.2f, 46.9f, 63.8f, 62.9f, 67.4f},
                {87.7f, 72.6f, 204f, 174f, 62f, 109f, 95.5f, 49.3f, 70.2f, 78.3f, 50.5f, 57.6f},
                {102f, 81.9f, 79.4f, 44.8f, 41.8f, 35.2f, 34.4f, 33.5f, 160f, 120f, 72.2f, 72.8f},
                {72.3f, 76.4f, 66.9f, 42.2f, 47.1f, 69.2f, 49.7f, 27.3f, 34.5f, 53f, 56.8f, 69f},
                {74.6f, 56.2f, 70.8f, 75.6f, 70f, 85.2f, 153f, 162f, 80.7f, 87.1f, 74.8f, 72.5f},
                {67.2f, 79.1f, 106f, 109f, 52.5f, 55.4f, 46.6f, 24.9f, 18.3f, 42.8f, 55.5f, 56.7f},
                {48.2f, 70.3f, 108f, 159f, 57.8f, 52.7f, 45.6f, 33.3f, 58.9f, 80.4f, 64.4f, 67.5f},
                {130f, 108f, 94.5f, 70.7f, 53.5f, 53.5f, 33.1f, 26.1f, 31.6f, 35.9f, 34.7f, 47.3f},
                {57f, 82.3f, 80.7f, 64.9f, 56.4f, 43.2f, 44.1f, 27.8f, 40.5f, 85f, 63.1f, 57f},
                {60.4f, 91.8f, 153f, 546f, 105f, 42.9f, 36.5f, 35.2f, 78.6f, 96.2f, 77.9f, 131f},
                {87f, 97.7f, 105f, 85.3f, 66.3f, 59.9f, 113f, 116f, 106f, 114f, 98.7f, 126f},
                {177f, 133f, 129f, 128f, 83.1f, 69.1f, 125f, 72.6f, 66.2f, 113f, 114f, 96.1f},
                {119f, 142f, 256f, 150f, 103f, 49.1f, 35f, 28.7f, 40.3f, 52.5f, 54.7f, 93.9f},
                {86.9f, 135f, 129f, 127f, 69.5f, 44.9f, 50.4f, 58.7f, 80f, 108f, 83.7f, 89.2f},
                {95.8f, 114f, 127f, 121f, 98.8f, 114f, 104f, 45.7f, 54.3f, 74.5f, 90.6f, 96.6f},
                {67.5f, 144f, 96.8f, 72.7f, 48.8f, 80.3f, 42.6f, 46.8f, 60.6f, 117f, 107f, 81.8f},
                {89.1f, 153f, 547f, 253f, 59.9f, 17.3f, 41.1f, 39.4f, 36.9f, 71.9f, 78.9f, 64.3f},
                {71.6f, 129f, 105f, 84.7f, 58f, 30.8f, 37.4f, 90f, 79.2f, 109f, 97.8f, 87f},
                {94.5f, 109f, 195f, 170f, 151f, 80.8f, 49.5f, 49.9f, 68.6f, 90.4f, 86.5f, 94.6f},
                {93f, 107f, 224f, 297f, 96.8f, 116f, 60.9f, 33.5f, 78.2f, 105f, 106f, 75.7f},
                {79.9f, 92.1f, 101f, 60.9f, 32.9f, 22.4f, 18.8f, 24.7f, 44f, 70.7f, 77.7f, 70.1f}
        };

        // Обчислюємо середнє для кожного місяця
        float[] averages = new float[12];
        for (int month = 0; month < 12; month++) {
            float sum = 0;
            for (float[] yearlyFlows : monthlyFlows) {
                sum += yearlyFlows[month];
            }
            averages[month] = sum / monthlyFlows.length;
        }
        return averages;
    }
}
