const Product = require('../models/product')
const mongoose = require('mongoose');

exports.product_get_all_product = (req, res, next) => {
    Product.find()
    .select('name price _id productImage')
    .exec()
    .then(docs => {
        const response= {
            count: docs.length,
            products: docs.map(doc => {
                return{
                    name: doc.name,
                    price: doc.price,
                    productImage: doc.productImage,
                    decrpition: doc.decrpition,
                    discount: doc.discount,
                    _id: doc._id,
                    request:{
                        type: "GET",
                        url:'http://localhost:3000/products/' + doc._id
                    }
                }
            })
        }
        // if (docs.length >= 0){
            res.status(200).json(response);
        // } else {
        //     res.status(404).json({
        //         message: "no entries out"
        //     })
        // }
    })
    .catch(err => {
        console.log(err);
        res.status(500).json({
            error: err
        });
    })
}

exports.product_add_product = (req, res, next) => {
    console.log(req.file)
    const product = new Product({
        id: new mongoose.Types.ObjectId(),
        name: req.body.name,
        price: req.body.price,
        productImage: req.file.path,
        decrpition: req.body.decrpition,
        discount: req.body.discount,
    });
    product.save()
    .then(result => {
        console.log(result);
        res.status(201).json({
            message: 'Created product sucessfully',
            createProduct: {
                name: result.name,
                    price: result.price,
                    _id: result._id,
                    decrpition: result.decrpition,
                    discount: result.discount,
                    request:{
                        type: "GET",
                        url:'http://localhost:3000/products/' + result._id
                    }
            }
        })
    })
    .catch(err => {
        console.log(err)
        res.status(500).json({ error: err })
    });
}