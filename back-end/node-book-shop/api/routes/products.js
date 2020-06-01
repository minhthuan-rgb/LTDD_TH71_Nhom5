const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const multer = require('multer');

const storage = multer.diskStorage({
    destination: function(req, file, cb){
        cb(null,'./uploads');
    },
    filename: function(req,file,cb){
        cb(null, new Date().toISOString() + file.originalname);
    }
});

const fileFilter = (req,file,cb) => {
    if (file.mimetype === 'image/jpep' || file.mimetype === 'image/png'){
        cb(new Error('message'),true);
    } else{
        cb(null,false);
    }
};

const upload = multer({
    storage: storage,
    // limits:{
    //     fileSize: 1024*1024 
    // },
    // fileFilter: fileFilter
});

const Product = require('../models/product')
const ProductController = require('../controller/products');

router.get('/', ProductController.product_get_all_product);

router.post('/', upload.single('productImage'), ProductController.product_add_product);

router.get('/:productId', (req, res, next) => {
    const id = req.params.productId;
    Product.findById(id)
        .select('name price _id productImage')
        .exec()
        .then(doc => {
            console.log("From database", doc);
            res.status(200).json({ 
                product: doc,
                request:{
                    type:'GET',
                    description: 'Get all products',
                    url:'http://localhost:3000/products'
                }
            }); 
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({ error: err })
        })
});

router.patch('/:productId', (req, res, next) => {
    const id = req.params.productId;
    const updateOps = {};
    for (const ops of req.body){
        updateOps[ops.propName] = ops.value;
    }
    Product.update({_id: id},{$set: updateOps})
    .exec()
    .then(result => {
        res.status(200).json({
            message:'Product updated',
            request:{
                type:'GET',
                url:'http://localhost:3000/products/' + id
            }
        });    
    })
    .catch(err => {
        console.log(err);
        res.status(500).json({ error: err })
    });
});


router.delete('/:productId', (req, res, next) => {
    const id = req.params.productId;
    Product.remove({_id: id})
    .exec()
    .then(result =>{
        res.status(200).json({
            message:'Product deleted',
            request:{
                type:'POST',
                url:'http://localhost:3000/products',
                body:{ name:'String', price: 'Number'}
            }
        });
    })
    .catch(err =>{
        console.log(err);
        res.status(500).json({ error: err })
    })
})

module.exports = router;