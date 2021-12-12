package main

import (
	"log"
	"user/biz/middleware"
	"user/kitex_gen/user_service/userservice"

	"github.com/cloudwego/kitex/server"
)

func main() {
	var options []server.Option
	options = append(options, server.WithMiddleware(middleware.RequestLogMW))
	svr := userservice.NewServer(new(UserServiceImpl), options...)

	err := svr.Run()

	if err != nil {
		log.Println(err.Error())
	}
}
