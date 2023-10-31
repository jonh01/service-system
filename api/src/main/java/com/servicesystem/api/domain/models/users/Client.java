package com.servicesystem.api.domain.models.users;

import java.time.LocalDateTime;
import java.util.UUID;

import com.servicesystem.api.domain.models.enums.RegisteredUserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tb_client")
public class Client extends AUser{

    private String cpf;

    @Override
	@Id
    @GeneratedValue(generator = "UUID")
	public UUID getId() {
		return super.getId();
	}
    
    @Column
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Column
    @Override
    public String getImage() {
        return super.getImage();
    }

    @Column
    @Override
    public String getName() {
        return super.getName();
    }

    @Column
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Column
    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Column
    @Override
    public RegisteredUserType getType() {
        return super.getType();
    }

    @Column
    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

}
