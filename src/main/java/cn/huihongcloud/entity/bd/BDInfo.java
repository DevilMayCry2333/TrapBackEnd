package cn.huihongcloud.entity.bd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 钟晖宏 on 2018/5/31
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BDInfo {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Result {
        private AddressComponent addressComponent;

        public AddressComponent getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponent addressComponent) {
            this.addressComponent = addressComponent;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class AddressComponent {

            private String adcode;
            private String province;
            private String city;
            private String area;
            private String town;

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }
        }
    }

}

