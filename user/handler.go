package main

import (
	"context"
	"user/biz/handler"
	"user/kitex_gen/user_service"
)

// UserServiceImpl implements the last service interface defined in the IDL.
type UserServiceImpl struct{}

// Register implements the UserServiceImpl interface.
func (s *UserServiceImpl) Register(ctx context.Context, req *user_service.RegisterRequest) (resp *user_service.RegisterResponse, err error) {
	return handler.Register(ctx, req)
}

// Login implements the UserServiceImpl interface.
func (s *UserServiceImpl) Login(ctx context.Context, req *user_service.LoginRequest) (resp *user_service.LoginResponse, err error) {
	return handler.Login(ctx, req)
}

// Authenticate implements the UserServiceImpl interface.
func (s *UserServiceImpl) Authenticate(ctx context.Context, req *user_service.AuthenticateRequest) (resp *user_service.AuthenticateResponse, err error) {
	return handler.Auth(ctx, req)
}
