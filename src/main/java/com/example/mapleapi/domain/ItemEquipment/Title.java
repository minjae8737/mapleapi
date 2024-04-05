package com.example.mapleapi.domain.ItemEquipment;

import lombok.Getter;
import lombok.Setter;

/**
 * "title": {`
 *     "title_name": "string",`
 *     "title_icon": "string",`
 *     "title_description": "string",`
 *     "date_expire": "2023-12-21T17:28+09:00",`
 *     "date_option_expire": "2023-12-21T17:28+09:00"`
 *   },
 */

@Getter @Setter
public class Title {

    private String title_name;
    private String title_icon;
    private String title_description;
    private String date_expire;
    private String date_option_expire;
}
