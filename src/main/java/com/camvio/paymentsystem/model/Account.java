package com.camvio.paymentsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
public class Account implements Serializable {

    private Integer id;
}
