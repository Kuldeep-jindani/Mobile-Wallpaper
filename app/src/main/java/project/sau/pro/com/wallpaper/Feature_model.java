package project.sau.pro.com.wallpaper;

public class Feature_model {


    public int id;
    private String img_url;
    private String category_name;


    public void setId(int id) {
        this.id = id;
    }

    public void setimg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setcategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImg_url()
    {
        return img_url;
    }

    public int getId() {
        return id;
    }

    public String getCategory_name()
    {
        return category_name;
    }


}
