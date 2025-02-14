const Order = require('../models/order');
const Product = require('../models/product')
const mongoose = require('mongoose');


exports.order_get_all =  (req, res, next) => {
    Order.find()
        .select('product quantity _id')
        .populate('product','name')
        .exec()
        .then(docs => {
            res.status(200).json({
                count: docs.length,
                orders: docs.map(doc => {
                    return {
                        _id: doc._id,
                        product: doc.product,
                        request: {
                            type: 'GET',
                            url: 'http://localhost:3000/orders/' + doc._id
                        }
                    }

                })
            });
        })
        .catch(err => {
            res.status(500).json({
                error: err
            })
        })
}

exports.orders_create_order = (req, res, next) => {
    const product = Product.find({productId: req.body.productId});
    if(!product){
        return res.status(404).json({
            message: "Product not found"
        });
    }
    const order = new Order({
        id: mongoose.Types.ObjectId(),
        quantity: req.body.quantity,
        product: req.body.productId
    });
    return res.status(200).json({
        message: "Order stored",
        createdOrder: {
            _id: order._id,
            product: order.product,
            quantity: order.quantity
        },
        request: {
            type: "GET",
            url: "http://localhost:3000/orders/" + order._id
        }
    });

  };
// export async function orders_create_order(res,req,next) {
//     const product = Product.find({productId: req.body.productId});
//     if(!product){
//         return res.status(404).json({
//             message: "Product not found"
//         });
//     }
//     const order = new Order({
//         id: mongoose.Types.ObjectId(),
//         quantity: req.body.quantity,
//         product: req.body.productId
//     });
//
//
// }

exports.order_get_one = (req, res, next) => {
    Order.findById(req.params.orderId)
        .exec()
        .then(order1 => {
            if(!order1){
                return res.status(404).json({
                    message: "Order not found"
                })
            }
            res.status(200).json({
                order: order1,
                request: {
                    type: 'GET',
                    url: 'http://localhost:3000/orders/'
                }
            })
        })
        .catch(err => {
            res.status(500).json({
                error: err
            })
        })
}

exports.order_delete_order = (req, res, next) => {
    Order.remove({ _id: req.params.orderId })
        .exec().
        then(result => {
            res.status(200).json({
                message: 'Order deleted',
                request: {
                    type: 'POST',
                    url: 'http://localhost:3000/orders/',
                    body: { productId: "ID", quantity: 'Number' }
                }
            })
        })
}