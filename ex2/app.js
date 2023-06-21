import http from "http";
import bodyParser from 'body-parser';
import mongoose from 'mongoose'
import routerChats from './routes/chat.js'
import routerUsers from './routes/user.js'
import routerToken from './routes/token.js'
import { Server } from 'socket.io';
import express from "express";
import cors from "cors";

import userService from "./services/user.js";

const app = express();
app.use(cors());
app.use(express.static('public'));
app.use(express.static('src'));
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: true }));
// app.use(bodyParser());
app.set('view engine', 'ejs');
app.use('/Chats', routerChats);
app.use('/Users', routerUsers);
app.use('/Tokens', routerToken);
mongoose.connect("mongodb://127.0.0.1:27017", {
    useNewUrlParser: true,
    useUnifiedTopology: true});


const server = http.createServer(app);
const io = new Server(server,{cors: {
        origin: "http://localhost:12345",
        methods: ['GET','POST','DELETE']
     }});


io.on("connection", (socket) => {
//   socket.emit("welcome", { message: "Welcome!", id: socket.id });

//   socket.on("i am client", console.log);
  socket.on("newMsg", function (msg){
            socket.broadcast.emit('check', msg)
        });
    });



const port = 12345;
server.listen(port, () => {
//   console.log(`Server listening on port ${port}`);
});

// userService.sendNotification("roi", "1234", "5678");
