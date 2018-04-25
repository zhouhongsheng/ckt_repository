const express = require('express')
const faker = require('faker/locale/zh_CN')
const logger = require('morgan')
const app = express()

var bodyParser=require('body-parser')
app.use(bodyParser.urlencoded({extended:false}))
app.use(bodyParser.json());

let count = 88
const users = new Array(count)
app.use(logger('combined'))

app.listen(9120, () => console.log("user sever listening on 9120"))

//创建88个用户
while (count > 0) {
    users[count] = {
        id: count,
        name: faker.name.title(),
        teacherId: parseInt(Math.random() * 88) + 1,
		courseId: parseInt(Math.random() * 88) + 1,
        des: faker.lorem.paragraph(),
		addr:faker.lorem.paragraph(),
    }
    count --
}

app.get('/health', (req, res) => {
    res.json({
        status: 'UP'
    })
})

app.post('/user/query_user', function(req, res,next) {
	//获取创建的用户根据id
	console.log(req.body.id);
    res.send(users[req.body.id]);    
}); 


app.get('/users', (req, res, next) => {
	//获取所有用户
    res.json(users)
})