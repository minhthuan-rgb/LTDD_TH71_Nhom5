const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

const Order = require('../models/order')
const Product = require('../models/product')

const OrderController = require('../controller/orders');

// Handle incoming GET requests to /orders
router.get('/',OrderController.order_get_all);

router.post('/', OrderController.orders_create_order);

router.get('/:orderId', OrderController.order_get_one);

router.delete('/:orderID', OrderController.order_delete_order);


module.exports = router;