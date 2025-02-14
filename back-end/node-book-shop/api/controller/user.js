const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt')

const User = require('../models/user')

exports.user_sign_up =   async (req, res, next) => {
    User.find({ email: req.body.email })
      .exec()
      .then(user => {
        if (user.length >= 1) {
          return res.status(409).json({
            message: "Mail exists"
          });
        } else {
          bcrypt.hash(req.body.password, 10, async (err, hash) => {
            if (err) {
              return res.status(500).json({
                error: err
              });
            } else {
              const user = new User({
                _id: new mongoose.Types.ObjectId(),
                email: req.body.email,
                password: hash,
                name: req.body.name,
                birthday: req.body.birthday,
                phonenumber: req.body.phonenumber
              });
              user
                .save()
                .then(result => {
                  console.log(result);
                  res.status(201).json({
                    message: "User created"
                  });
                })
                .catch(err => {
                  console.log(err);
                  res.status(500).json({
                    error: err
                  });
                });
            }
          });
        }
      });
  }

exports.user_login =  async (req,res,next) =>{
    User.findOne({email: req.body.email})
    .exec()
    .then(user => {
      if(user.length < 1) {
        return res.status(401).json({
          message: "Auth failed"
        });
      }
      bcrypt.compare(req.body.password, user._doc.password, async (err,result) => {
        if(err){
          return res.status(401).json({
            message: "Auth failed"
          });
        }
        if (result){
          return res.status(200).json({
            message: "Auth sucessful"
          })
        }
        res.status(401).json({
          message: "Auth failed"
        });
      })
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
  }

exports.user_delete =  async (req, res, next) => {
    User.remove({ _id: req.params.userId })
      .exec()
      .then(result => {
        res.status(200).json({
          message: "User deleted"
        });
      })
      .catch(err => {
        console.log(err);
        res.status(500).json({
          error: err
        });
      });
}

exports.user_reset_pass = async(req, res, next) =>{
  User.findOne({email: req.body.email})
    .exec()
    .then(async user => {
      if(user.length < 1) {
        res.status(404).json({
          error: err
        })
      } else if(req.body.password == req.body.comfinpass){
        User.updateOne({email: req.body.email},{password: req.body.password})
        .exec()
        .then(result => {
          console.log(result);
          res.status(201).json({
            message: "success change password"
          });
        })
        .catch(err => {
          console.log(err);
          res.status(500).json({
            error: err
          });
        });
      } else {
        res.status(500).json({
          message:"wrong confim password please retry"
        })
      }
    })
    .catch(err => {
      console.log(err);
      res.status(404).json({
        error: err
      })
    })
}
