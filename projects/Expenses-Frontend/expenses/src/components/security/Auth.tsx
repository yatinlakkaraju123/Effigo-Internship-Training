import { createContext, useContext, useState, ReactNode } from "react";
import { executeJWTAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";
import { jwtDecode } from "jwt-decode";

// Define the shape of AuthContext
interface AuthContextType {
  login: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
  isAuthenticated: boolean;
  username: string | null;
  token: string | null;
  userId: number;
  role: string | null;
}

// Define the shape of the decoded JWT token
interface DecodedToken {
  userId: string;
  scope: string; // Assuming "scope" holds the role
}

// Default context values
const defaultAuthContext: AuthContextType = {
  login: async () => false,
  logout: () => {},
  isAuthenticated: false,
  username: null,
  token: null,
  userId: -1,
  role: null,
};

export const AuthContext = createContext<AuthContextType>(defaultAuthContext);
export const useAuth = () => useContext(AuthContext);

interface AuthProviderProps {
  children: ReactNode;
}

function AuthProvider({ children }: AuthProviderProps) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState<string | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [userId, setUserId] = useState<number>(-1);
  const [role, setRole] = useState<string | null>(null);

  async function login(username: string, password: string): Promise<boolean> {
    console.log("Logging in with username:", username);

    try {
      const response = await executeJWTAuthenticationService(username, password);
      console.log("JWT Token Response:", response);

      if (response.status === 200) {
        const retrievedToken = response.data.token;
        const jwtToken = `Bearer ${retrievedToken}`;
        
        // Decode JWT token
        const decodedToken = jwtDecode<DecodedToken>(retrievedToken);
        console.log("Decoded Token:", decodedToken);

        setUsername(username);
        setToken(jwtToken);
        setUserId(Number(decodedToken.userId));
        setRole(decodedToken.scope);
        setIsAuthenticated(true);

        // Attach token to API client
        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      console.error("Login error:", error);
      logout();
      return false;
    }
  }

  function logout() {
    setIsAuthenticated(false);
    setUsername(null);
    setToken(null);
    setUserId(-1);
    setRole(null);
  }

  return (
    <AuthContext.Provider value={{ login, logout, isAuthenticated, username, token, userId, role }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;
