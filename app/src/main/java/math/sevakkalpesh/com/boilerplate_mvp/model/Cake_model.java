package math.sevakkalpesh.com.boilerplate_mvp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Cameron Stobie - Waracle tech test  on 10/14/2015.
 */


public class Cake_model {

    @Expose
    private String title;
    @Expose
    private String desc;
    @Expose
    private String image;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc
     * The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
