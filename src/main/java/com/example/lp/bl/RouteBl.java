package com.example.lp.bl;

public class RouteBl {
   /* @Autowired

    UserInfoRepository userInfoRepository;


    public CustomerBl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfoEntity findPersonById(int pk) {
        Optional<UserInfoEntity> optional = this.userInfoRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }

    public List<UserInfoDto> findAllUsers() {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        for (UserInfoEntity userInfoEntity:userInfoRepository.findAllByUserInfoStatus(Status.ACTIVE.getStatus())) {
            userInfoDtoList.add(new UserInfoDto(userInfoEntity));
        }
        return userInfoDtoList;
    }*/
}
