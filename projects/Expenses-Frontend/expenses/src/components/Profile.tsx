import { useEffect, useState } from "react";
import { retrieveUserById } from "./api/UserService";
import UserNavbar from "./Navbars/UserNavbar";
import { useAuth } from "./security/Auth";
import { UserProfileInterface } from "../Interfaces";

function Profile() {
  const auth = useAuth();
  const [globalProfile, setGlobalProfile] = useState<UserProfileInterface>({
    activatedProfile: false,
    address: "",
    email: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    username: auth.username,
  });

  const retrieveProfile = async () => {
    try {
      const response = await retrieveUserById(auth.userId);
      setGlobalProfile(response.data.profile);
    } catch (error) {
      console.error("Error fetching profile:", error);
    }
  };

  useEffect(() => {
    retrieveProfile();
  }, []);

  return (
    <div>
      <UserNavbar />
      <div className="container mt-4">
        <h2 className="text-center">Profile Information</h2>
        <div className="card shadow-lg p-4 mt-3">
          <div className="card-body">
            <h5 className="card-title text-center mb-3">User Details</h5>
            {globalProfile ? (
              <ul className="list-group">
                <li className="list-group-item">
                  <strong>Profile Activation:</strong>{" "}
                  <span className={`badge ${globalProfile.activatedProfile ? "bg-success" : "bg-danger"}`}>
                    {globalProfile.activatedProfile ? "Active" : "Inactive"}
                  </span>
                </li>
                <li className="list-group-item">
                  <strong>Username:</strong> {globalProfile.username}
                </li>
                <li className="list-group-item">
                  <strong>Name:</strong> {globalProfile.firstName} {globalProfile.lastName}
                </li>
                <li className="list-group-item">
                  <strong>Email:</strong> {globalProfile.email}
                </li>
                <li className="list-group-item">
                  <strong>Phone Number:</strong> {globalProfile.phoneNumber}
                </li>
                <li className="list-group-item">
                  <strong>Address:</strong> {globalProfile.address}
                </li>
              </ul>
            ) : (
              <div className="alert alert-danger text-center">Profile not found.</div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Profile;
