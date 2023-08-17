const path = require("path");

module.exports = {
  entry: [
    "./components/BreadcrumbsMenu/index.jsx",
    "./components/ControlledRadioButtons/index.jsx",
    "./components/ControlledRadioButtons/PaymentMethod.jsx",
    "./components/CustomTabPanels/CustomTabPanels.jsx",
    "./components/Dialogs/AddCar.jsx",
    "./components/Dialogs/ViewDetails.jsx",
    "./components/Footer/index.jsx",
    "./components/LoginForm/index.jsx",
    "./components/Modals/ConfirmDeposit.jsx",
    "./components/Modals/ConfirmPayment.jsx",
    "./components/Modals/LogOutt.jsx",
    "./components/Modals/NotifyReset.jsx",
    "./components/Modals/ResetSuccess.jsx",
    "./components/Modals/ReturnCar.jsx",
    "./components/Modals/Review.jsx",
    "./components/Modals/Top-up.jsx",
    "./components/Modals/Withdraw.jsx",
    "./components/NavMenuHomeGuest/index.jsx",
    "./components/NavMenuUser/index.jsx",
    "./components/RentNow/index.jsx",
    "./components/RentNow/BookingSteps/BookingInformation.jsx",
    "./components/RentNow/BookingSteps/BookingSummary.jsx",
    "./components/RentNow/BookingSteps/Finish.jsx",
    "./components/RentNow/BookingSteps/Payments.jsx",
    "./components/ScrollTop/index.jsx",
    "./components/Select/BrandsSelection.jsx",
    "./components/Select/ColorSelection.jsx",
    "./components/Select/ModelsSelection.jsx",
    "./components/Select/NumberOfSeats.jsx",
    "./components/Select/ProductionYearSelection.jsx",
    "./components/Select/Provinces.jsx",
    "./components/SignUpForm/index.jsx",
    "./components/Stepper/AddCarStepper.jsx",
    "./components/Stepper/AutoPlaySwipePreview.jsx",
    "./components/Stepper/Steps/Basic.jsx",
    "./components/Stepper/Steps/Details.jsx",
    "./components/Stepper/Steps/Preview.jsx",
    "./components/Stepper/Steps/Pricing.jsx",
    "./components/UploadFile/BackOfCar.jsx",
    "./components/UploadFile/LeftOfCar.jsx",
    "./components/UploadFile/FrontOfCar.jsx",
    "./components/UploadFile/RightOfCar.jsx",
    "./components/UploadFile/Certificate.jsx",
    "./components/UploadFile/DrivingLicense.jsx",
    "./components/UploadFile/Insurance.jsx",
    "./components/UploadFile/RegistrationPaper.jsx",
    "./components/UserMenu/index.jsx",
    "./components/Layout.jsx",
    "./containers/Account/Booking/BookingDetails.jsx",
    "./containers/Account/Booking/index.jsx",
    "./containers/Account/Car/EditCarDetails.jsx",
    "./containers/Account/Car/ViewCarDetails.jsx",
    "./containers/Account/Car/index.jsx",
    "./containers/Account/Feedback/index.jsx",
    "./containers/Account/Profile/index.jsx",
    "./containers/Account/Reset/ResetPass.jsx",
    "./containers/Account/Reset/ConfirmReset.jsx",
    "./containers/Account/Wallet/index.jsx",
    "./containers/Home/HomeGuest.jsx",
    "./containers/Home/HomeCustomer.jsx",
    "./containers/Home/HomeOwner.jsx",
  ], // Đường dẫn tới tệp gốc của mã nguồn JSX
  output: {
    filename: "bundle.js", // Tên tệp đầu ra sau khi biên dịch
    path: path.resolve(__dirname, "dist"), // Thư mục đầu ra
  },
  mode: "development",
  resolve: {
    extensions: [".js", ".jsx"], // Cho phép import các tệp .js và .jsx mà không cần chỉ định phần mở rộng
  },
  module: {
    rules: [
      {
        test: /\.jsx?$/, // Biên dịch cả .js và .jsx
        exclude: /node_modules/,
        use: {
          loader: "babel-loader", // Sử dụng babel để biên dịch JSX
        },
      },
    ],
  },
};
