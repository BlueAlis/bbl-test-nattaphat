package org.interview.bbl.mapper;

import org.interview.bbl.dto.request.AddressRequest;
import org.interview.bbl.dto.request.CompanyRequest;
import org.interview.bbl.dto.request.GeoRequest;
import org.interview.bbl.dto.request.UserRequest;
import org.interview.bbl.dto.response.AddressResponse;
import org.interview.bbl.dto.response.CompanyResponse;
import org.interview.bbl.dto.response.GeoResponse;
import org.interview.bbl.dto.response.UserResponse;
import org.interview.bbl.model.entity.Address;
import org.interview.bbl.model.entity.Company;
import org.interview.bbl.model.entity.Geo;
import org.interview.bbl.model.entity.User;
import org.interview.bbl.model.enums.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) return null;
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setWebsite(user.getWebsite());
        response.setStatus(user.getStatus());
        response.setAddress(toAddressResponse(user.getAddress()));
        response.setCompany(toCompanyResponse(user.getCompany()));
        return response;
    }

    public User toEntity(UserRequest request) {
        if (request == null) return null;
        return User.builder()
                .id(request.getId())
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .status(request.getStatus() != null ? request.getStatus() : UserStatus.ACTIVE)
                .address(toAddressEntity(request.getAddress()))
                .company(toCompanyEntity(request.getCompany()))
                .build();
    }

    public void updateEntity(User user, UserRequest request) {
        if (request.getName() != null) user.setName(request.getName());
        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getWebsite() != null) user.setWebsite(request.getWebsite());
        if (request.getStatus() != null) user.setStatus(request.getStatus());
        if (request.getAddress() != null) user.setAddress(toAddressEntity(request.getAddress()));
        if (request.getCompany() != null) user.setCompany(toCompanyEntity(request.getCompany()));
    }

    private AddressResponse toAddressResponse(Address address) {
        if (address == null) return null;
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setSuite(address.getSuite());
        response.setCity(address.getCity());
        response.setZipcode(address.getZipcode());
        response.setGeo(toGeoResponse(address.getGeo()));
        return response;
    }

    private CompanyResponse toCompanyResponse(Company company) {
        if (company == null) return null;
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setCatchPhrase(company.getCatchPhrase());
        response.setBs(company.getBs());
        return response;
    }

    private GeoResponse toGeoResponse(Geo geo) {
        if (geo == null) return null;
        GeoResponse response = new GeoResponse();
        response.setLat(geo.getLat());
        response.setLng(geo.getLng());
        return response;
    }

    private Address toAddressEntity(AddressRequest request) {
        if (request == null) return null;
        return Address.builder()
                .street(request.getStreet())
                .suite(request.getSuite())
                .city(request.getCity())
                .zipcode(request.getZipcode())
                .geo(toGeoEntity(request.getGeo()))
                .build();
    }

    private Company toCompanyEntity(CompanyRequest request) {
        if (request == null) return null;
        return Company.builder()
                .name(request.getName())
                .catchPhrase(request.getCatchPhrase())
                .bs(request.getBs())
                .build();
    }

    private Geo toGeoEntity(GeoRequest request) {
        if (request == null) return null;
        return Geo.builder()
                .lat(request.getLat())
                .lng(request.getLng())
                .build();
    }
}

