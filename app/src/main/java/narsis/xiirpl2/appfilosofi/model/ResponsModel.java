package narsis.xiirpl2.appfilosofi.model;

import java.util.List;

public class ResponsModel {
    String message;
    int status;
    List<DataBarangModel> data;

    public List<DataBarangModel> getData() {
        return data;
    }

    public void setData(List<DataBarangModel> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
