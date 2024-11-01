/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.metadata;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author DeliveryExpress
 */
@Data
public class Tags {

    @Expose
    @DatabaseField(id = true) // Campo ID
    String userId;
    @Expose
    @DatabaseField // Campo ID
    String tags;

    private static final String SEPARTOR = " ";

    /**
     * *
     *
     * @param tag muste be without space and uppercase, us _ for separation word
     */
    public void addTag(String tag) {
        tags += ajustInputTag(tag) + SEPARTOR;
    }

    private boolean contains(String tag) {
        return tags.contains(ajustInputTag(tag));

    }

    private String ajustInputTag(String tag) {
        return tag.replace(" ", "").toUpperCase();

    }
    
    
    //use example   System.out.println("Is Delivery Mod? " + Tags.DeliveryTags.isMod(tags));
    public static  class DeliveryTags {

        public static  boolean isMod(Tags tags) {
            return tags.contains("IS_MOD");
        }

    }

    public static  class BussinesTags {

        public static  boolean isMod(Tags tags) {
            return tags.contains("IS_VIP");
        }

    }

}
