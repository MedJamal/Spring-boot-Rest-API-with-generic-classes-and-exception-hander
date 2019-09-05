package com.elouazzani.servicesImpl;

import org.springframework.stereotype.Service;

import com.elouazzani.entities.Product;
import com.elouazzani.services.ProductService;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

}
