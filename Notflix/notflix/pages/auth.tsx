import Input from "@/components/input";
import { useCallback, useState } from "react";
import axios from "axios";
import { signIn } from 'next-auth/react';
import { useRouter } from "next/router";

import { FcGoogle } from 'react-icons/fc';
import { FaGithub } from 'react-icons/fa';

const Auth = () => {
    const router = useRouter();
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [mysteryCode, setMysteryCode] = useState('');

    const [isLogin, setIsLogin] = useState(true);

    const toggleLogin = useCallback(() => {
        setIsLogin((currentLogin) => currentLogin === true ? false : true)
    }, []);

    const login = useCallback(async() => {
        try {
            await signIn('credentials', {
                email,
                password,
                redirect: false,
                callbackUrl: '/'
            })

            router.push('/');
        } catch (error) {
            console.log(error)
        }
    },[email, password, router]);

    const signUp = useCallback(async() => {
        try {
            await axios.post(`/api/signUp`, {
                email,
                name,
                password,
                mysteryCode
            })

            login();
        } catch (error) {
            console.log(error)
        }
    },[email, name, password, mysteryCode, login]);

    

    return (
        <div className="relative h-full w-full bg-[url('/images/hero-3.jpg')] bg-no-repeat bg-center bg-fixed bg-cover">
            <div className="bg-black w-full h-full lg:bg-opacity-50">
                <nav className="px-12 py-5">
                    <img src="/images/logo-2.png" alt="notflix logo" className="h-12"/>
                </nav>
                <div className="flex justify-center">
                    <div className="bg-black bg-opacity-70 px-16 py-16 self-center mt-2 lg:w-2/5 lg:max-w-md rounded-md w-full">
                        <h2 className="text-white text-4xl mb-8 font-semibold">
                            {isLogin ? 'Sign in' : 'Create Account'}
                        </h2>
                        <div className="flex flex-col gap-4">
                            {!isLogin && (
                            <>
                                <Input label="Username" id='name' onChange={(ev: any) => setName(ev.target.value)} value={name}/>
                                <Input label="Mystery Code" id='mysteryCode' onChange={(ev: any) => setMysteryCode(ev.target.value)} value={mysteryCode}/>
                            </>
                                
                            )}
                            <Input label="Email" id='email' type="email" onChange={(ev: any) => setEmail(ev.target.value)} value={email}/>
                            <Input label="Password" id='password' type='password' onChange={(ev: any) => setPassword(ev.target.value)} value={password}/>
                        </div>
                        <button onClick={isLogin ? login : signUp} className="bg-red-600 py-3 text-white rounded-md w-full mt-10 hover:bg-red-700 transition">{isLogin ? 'Login' : 'Register'}</button>
                        <div className="flex flex-row items-center gap-4 mt-8 justify-center">
                            <div
                                className="
                                    w-10
                                    h-10
                                    bg-white
                                    rounded-full
                                    flex
                                    items-center
                                    justify-center
                                    cursor-pointer
                                    hover:opacity-80
                                    transition
                                "
                            >
                                <FcGoogle size={35}/>
                            </div>
                            <div
                                className="
                                    w-10
                                    h-10
                                    bg-white
                                    rounded-full
                                    flex
                                    items-center
                                    justify-center
                                    cursor-pointer
                                    hover:opacity-80
                                    transition
                                "
                            >
                                <FaGithub size={35}/>
                            </div>
                        </div>
                        <p className="text-neutral-500 mt-12">
                            {isLogin ? 'First time using Notflix?' : 'Already have an account?'}
                            <span onClick={toggleLogin} className="text-white ml-1 hover:underline cursor-pointer">
                                {isLogin ? 'Create an account' : 'Login'}
                            </span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Auth;