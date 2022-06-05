package com.gft.gdesk.service;

//@Service
//public class AdminService
//{
//    private final UserRepository userRepository;
//    private static final String WAITING_FOR_APPROVAL = "WAITING_FOR_APPROVAL";
//    private static final String APPROVED = "APPROVED";
//
//
//    public AdminService(UserRepository userRepository)
//    {
//        this.userRepository = userRepository;
//    }
//
//    public void acceptNewUser(Long Id)
//    {
//        Optional<User> user = Optional.ofNullable(userRepository.findAllById(Id));
//
//        if(!user.isPresent())
//        {
//            throw new UserNotFoundException("User does not exist");
//        }
//
//        User userToUpdate = user.get();
//        if(userToUpdate.getStatus().equals(WAITING_FOR_APPROVAL))
//        {
//            userToUpdate.setStatus(APPROVED);
//        }
//        userRepository.save(userToUpdate);
//    }
//}