const mongoose = require('mongoose')

const productSchema = mongoose.Schema({
    name: {type: String, require: true},
    price: {type: Number, require: true},
    productImage: {type: String, require: true},
    decrpition: {type: String, require:true},
    discount: {type: Number, require:false}
})

module.exports = mongoose.model('Product', productSchema)