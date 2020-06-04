const http = require('http');
const app = require('./app');

const port = process.env.PORT || 3000;

const server = http.createServer(app);

server.address({address: '0.0.0.0', family: 'IPv4', port:'3000'})

server.listen(port,(error) => {
    if (error){
        console.log('server error')
    } else {
        console.log('server is running')
    }

});