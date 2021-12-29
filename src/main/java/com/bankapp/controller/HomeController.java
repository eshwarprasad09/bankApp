{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @GetMapping("/")
    public String home(){
        return "Welcome to HSBC Bank";
    }

    @PostMapping("/openaccount")
    public ResponseEntity<User> openAccount(@RequestBody UserDto userDto){
        User user = userService.openAccount(userDto);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getbalance/{accountNo}")
    public ResponseEntity<BalanceEnquiry> getBalance(@PathVariable("accountNo") String accountNo) {
        if (!userService.isAccountExists(accountNo)) {
            return new ResponseEntity<BalanceEnquiry>(HttpStatus.BAD_REQUEST);
        }
        BalanceEnquiry balance = userService.getBalance(accountNo);
        return new ResponseEntity<BalanceEnquiry>(balance, HttpStatus.ACCEPTED);
    }

    @PostMapping("/moneytransfer")
    public String moneyTransfer(@RequestBody MoneyTransferDto moneyTransferDto){
        String toAccount = moneyTransferDto.getToAccount();
        String fromAccount = moneyTransferDto.getFromAccount();
        userService.moneyTransfer(toAccount, fromAccount, moneyTransferDto);
        return "Transfer Success";
    }

    @GetMapping("/getuser/{accountNo}")
    public ResponseEntity<User> getUser(@PathVariable("accountNo") String accountNo){
        User user = userService.getUserByAccountNo(accountNo);
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @GetMapping("/accounthistory/{accountNo}")
    public ResponseEntity<List> getAccountHistory(@PathVariable("accountNo") String accountNo){
        List accountHistory = userService.getAccountHistory(accountNo);
        return new ResponseEntity<List>(accountHistory, HttpStatus.FOUND);
    }

    @GetMapping("/loan")
    public ResponseEntity<LoanStatus> loanStatus(@RequestBody LoanDto loanDto){
        User user = userService.getUserByAccountNo(loanDto.getAccountNo());
        LoanStatus loanStatus = userService.getLoanStatus(loanDto);
        return new ResponseEntity<LoanStatus>(loanStatus, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        User user = userService.getLogin(loginDto);
        if(user != null){
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                return role.getName() + " Login Success";
            }
            return "Login Success";
        }
        else{
            return "Invalid user";
        }
    }

    //checked out feature branch eshwarprasad
    //checked out feature branch eshwarprasad one more time

//    checked out sprint
    //checked out feature

    //checked out feature branch

}
