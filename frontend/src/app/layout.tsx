import './globals.css';
import type { Metadata } from 'next';
import Navbar from '@/components/layout/Navbar';

export const metadata: Metadata = {
  title: 'WorkShift | Part-Time Job Platform',
  description: 'Connect employers with temporary workers',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en" className="h-full bg-gray-50">
      <body className="flex min-h-screen flex-col">
        <Navbar />
        <main className="container mx-auto flex-grow p-4">{children}</main>
        <footer className="bg-gray-800 p-4 text-center text-white">
          Copyright {new Date().getFullYear()} WorkShift
        </footer>
      </body>
    </html>
  );
}
