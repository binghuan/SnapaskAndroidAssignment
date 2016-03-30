package com.bh.android.snapasktest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MyUnitTest {
    @Test
    public void dataParserTest() throws Exception {

        String testData = "{\n" +
                "id: 723,\n" +
                "gender: \"male\",\n" +
                "name: {\n" +
                "first: \"adrian\",\n" +
                "last: \"martinez\"\n" +
                "},\n" +
                "email: \"adrian.martinez@example.com\",\n" +
                "username: \"greenbear131\",\n" +
                "registered: 1153910367,\n" +
                "dob: 1290592394,\n" +
                "school: \"HKU\",\n" +
                "country_code: \"852\",\n" +
                "phone: \"12345678\",\n" +
                "profile_pic_url: \"https://randomuser.me/api/portraits/men/5.jpg\",\n" +
                "role: {\n" +
                "id: 3,\n" +
                "name: \"Tutor\"\n" +
                "},\n" +
                "rating: 4.3,\n" +
                "rating_total: 5\n" +
                "}";

        UserData dataA = new UserData();
        dataA.email = "adrian.martinez@example.com";

        UserData dataB = DataParser.parseUserData(testData);

        assertEquals(dataA.email, dataB.email);
    }
}